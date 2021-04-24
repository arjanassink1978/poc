@ProxyApi
Feature: Movie

  Scenario Outline: Post a new Movie
    Given get token with credential "testuser"
    When I create and post a movie payload with
      | imdb   | title   | type   | year   |
      | <imdb> | <title> | <type> | <year> |
    Then The responsecode should be <responsecode>
    Examples:
      | imdb     | title             | type  | year | responsecode |
      | whatever | Testing goes wild | movie | 2018 | 200          |