Feature: Validate Pie Chart Slices

  Scenario Outline: Validate pie chart slices percentage on click
    Given I am on the Playground AnyChart page
    When I switch to "pieChart"
    And I click slice with suffix "<suffix>"
    Then I should see percentage "<percentage>" in chart

    Examples:
      | suffix | percentage |
      | t      | 41.7%      |
      | u      | 16.7%      |
      | v      | 16.7%      |
      | w      | 16.7%      |
      | x      | 8.3%       |
