# Deck Derby API

## Run

`sam build && sam local start-api --warm-containers eager`

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

## Endpoint(s) Design - No Auth

<details>
    <summary>/register</summary>

```python
class Request:
    email: str
    username: str
    password: str

class Response:
    message: str
    userId: str
```

</details>

<details>
    <summary>/login</summary>

```python
class Request:
    username: str
    password: str

class Response:
    message: str
    userId: str
    username: str
    totalWinnings: int
```

</details>

<details>
    <summary>/getUser</summary>

```python
class Request:
    username: str

class Response:
    message: str
    userId: str
    username: str
    totalWinnings: int
```

</details>

## Endpoint(s) Design

<details>
    <summary>/register</summary>

```python
class Request:
    email: str
    username: str
    password: str

class Response:
    message: str
    userId: str
```

</details>

<details>
    <summary>/login</summary>

```python
class Request:
    username: str
    password: str

class Response:
    message: str
    userId: str
    accessToken: str
    refreshToken: str
```

</details>

<details>
    <summary>/logout</summary>

```python
class Request:
    accessToken: str

class Response:
    message: str
```
</details>
