# Deck Derby API

## Design Document

- I want to have users, where each user can login/register with Deck Derby.
- Users should have their total winnings available to them, with an initial value of 100 drinks
- Users should have a username and email. Each email should be unique. Each username should be unique.
- Users should have a password, where only the salt and hash are saved.

&nbsp;

## Next Steps:
- Create RDS instance on AWS (try to automate with CodeBuild using this repo)
- Create API Gateway endpoints (same as above)
- Upload to Lambda