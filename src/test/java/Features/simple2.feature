Feature: Test simple web launch

@smoke
Scenario: Verify to launch simple site with home
Given I will see "Learn to Code"

@reg
Scenario: Verify to launch simple site with home
Given I click "Not Sure Where To Begin?" Link
Then I will see "Where To Start"

@sanity
Scenario: Verify to launch simple site with home
Given I click "Not Sure Where To Begin?" Link
Then I will see "Where To Start"