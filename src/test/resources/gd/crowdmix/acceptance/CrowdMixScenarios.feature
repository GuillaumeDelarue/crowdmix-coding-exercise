Feature: CrowdMix social networking application

  Scenario: Alice can publish a message to a personal timeline
  The timeline then displays the list of messages and the elapsed time since they were published
    Given system time is now 10:37:00
    And processing command Alice -> I love the weather today
    And system time is now 10:40:00
    When processing command Alice
    Then output is
      | I love the weather today (3 minutes ago) |

  Scenario: Bob can publish multiple messages and list them from the most recent to the oldest
    Given system time is now 10:38:00
    And processing command Bob -> Damn! we lost!
    And system time is now 10:39:00
    And processing command Bob -> Good game though
    And system time is now 10:40:00
    When processing command Bob
    Then output is
      | Good game though (1 minute ago) |
      | Damn! we lost! (2 minutes ago)  |

  Scenario: Charlie can subscribe to Alice's and Bob's timeline, and view an aggregated list of all subscriptions
    Given system time is now 10:35:00
    And processing command Alice -> I love the weather today
    And system time is now 10:38:00
    And processing command Bob -> Damn! we lost!
    And system time is now 10:39:00
    And processing command Bob -> Good game though
    And system time is now 10:39:58
    And processing command Charlie -> I'm in New York today! Anyone wants to have a coffee?
    And processing command Charlie follows Alice
    And processing command Charlie follows Bob
    And system time is now 10:40:00
    When processing command Charlie wall
    Then output is
      | Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago) |
      | Bob - Good game though (1 minute ago)                                           |
      | Bob - Damn! we lost! (2 minutes ago)                                            |
      | Alice - I love the weather today (5 minutes ago)                                |
