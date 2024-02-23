Feature: sample google test


  Scenario: search product at google

    Given User at google home page
    When User enter "1.5 ton 5 star split ac"
    Then User should see the result