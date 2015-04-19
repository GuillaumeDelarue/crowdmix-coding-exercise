Feature: CrowdMix social networking application

  Scenario: Alice can publish a message to a personal timeline
  The timeline then displays the list of messages and the elapsed time since they were published
    Given next command was issued 3 minutes ago
    And processing command Alice -> I love the weather today
    When processing command Alice
    Then output is
      | I love the weather today (3 minutes ago) |

  Scenario: Bob can publish multiple messages and list them from the most recent to the oldest
    Given next command was issued 2 minutes ago
    And processing command Bob -> Damn! we lost!
    And next command was issued 1 minute ago
    And processing command Bob -> Good game though
    When processing command Bob
    Then output is
      | Good game though (1 minute ago) |
      | Damn! we lost! (2 minutes ago)  |

  Scenario: Charlie can subscribe to Alice's and Bob's timelines, and view an aggregated list of all subscriptions
    Given next command was issued 2 seconds ago
    And processing command Charlie -> I'm in New York today! Anyone wants to have a coffee?
    And processing command Charlie follows Alice
    When processing command Charlie wall
    Then output is
      | Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago) |
      | Alice - I love the weather today (5 minutes ago)                                |
    When processing command Charlie follows Bob
    And processing command Charlie wall
    Then output is
      | Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago) |
      | Bob - Good game though (1 minute ago)                                           |
      | Bob - Damn! we lost! (2 minutes ago)                                            |
      | Alice - I love the weather today (5 minutes ago)                                |
