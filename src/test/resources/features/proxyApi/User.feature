@ProxyApi
Feature: User with proxy

  Scenario Outline: get valid user by name
    Given get token with credential "testuser"
    When I create a get request to get user by username
      | username   |
      | <username> |
    Then The responsecode should be <responsecode>
    And The userResponse contains if responsecode is 200
      | active   | id   | role   | username   |
      | <active> | <id> | <role> | <username> |
    Examples:
      | username   | responsecode | active | id | role  |
      | testuser   | 200          | true   | 2  | user  |
      | testadmin  | 200          | true   | 1  | admin |
      | idontexist | 404          |        |    |       |




