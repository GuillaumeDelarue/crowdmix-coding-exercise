package gd.crowdmix.data;

public interface Repository {

    User findOrCreateUser(String username);
}
