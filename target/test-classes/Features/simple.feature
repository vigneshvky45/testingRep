Feature: Test simple web launch

Scenario: Verify to launch simple site
Given I am logged in as "vignesh"
Then I will see "Home"
#But I will not see "Username"