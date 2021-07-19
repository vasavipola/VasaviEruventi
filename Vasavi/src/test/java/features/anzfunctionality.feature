Feature: Verify anz application functionality

Scenario: First Scenario
Given Open ANZ URL
When enter the all required fields
And click on the button
Then Verify expected amount

Scenario: Second Scenario
Given Open ANZ URL
When enter the all required fields
And click on the button
And click on start over button
Then Verify all the fields are get cleared

Scenario: Third Scenrio
Given Open ANZ URL
When enter one dollar in leaving expenses field
And click on the button
Then Verify the expected message

