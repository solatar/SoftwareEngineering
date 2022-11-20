Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  user "Antti" and password "Antti123" are entered
        Then  system will give message "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  user "pekka" and password "Pekka123" are entered
        Then  system will give message "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  user "ai" and password "aijai123" are entered
        Then  system will give message "new user not registered"
       
    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  user "Ritva" and password "ritu123" are entered
        Then  system will give message "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  user "Mimmi" and password "lehmajavaris" are entered
        Then  system will give message "new user not registered"
