@ProxyApi
Feature: Token

  Scenario Outline: Get token
    Given credentials "<credentials>" are used
    When I request a token
    Then The responsecode should be <responsecode>
    Examples:
      | credentials     | responsecode |
      | testadmin       | 200          |
      | testuser        | 200          |
      | invalidusername | 500          |
      | wrongpassword   | 400          |
      | wrongid         | 400          |