@UserApi
Feature: User without proxy

  Scenario Outline: Create User
    Given The user "<userName>" doesnt exist
    When I create and post a user payload with
      | userName   | role   | passWord   | active   |
      | <userName> | <role> | <passWord> | <active> |
    Then The responsecode should be <responsecode>
    And The errormessage should be "<errormessage>"
    Examples:
      | userName | role  | passWord | active | responsecode | errormessage                              |
      | admin1   | admin | test     | false  | 201          |                                                       |
      | admin2   | admin | test     | true   | 201          |                                                       |
      | test1    | user  | test     | false  | 201          |                                                       |
      | test2    | user  | test     | true   | 201          |                                                       |
      | test     | test  | test     | false  | 400          | Role: test not valid must be either admin or user     |
      |          | user  | test     | false  | 500          | Object reference not set to an instance of an object. |
      | test     |       | test     | false  | 500          | Object reference not set to an instance of an object. |
      | test     | admin |          | false  | 400          | Value cannot be null. (Parameter 'input') |



