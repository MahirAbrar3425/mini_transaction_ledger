Got it! Here's a simple `README.md` exactly based on what you provided:

````markdown
# Transaction Ledger Docker Setup

## Install Docker Desktop

Make sure Docker Desktop is installed on your machine.

## Pull the images

```bash
docker pull mahirabrar3425/transaction-ledger-backend:latest
docker pull mahirabrar3425/transaction-ledger-frontend:latest
````

## Create a minimal `docker-compose.yml`

```yaml
services:
  backend:
    image: mahirabrar3425/transaction-ledger-backend:latest
    ports:
      - "8080:8080"

  frontend:
    image: mahirabrar3425/transaction-ledger-frontend:latest
    ports:
      - "3000:80"
    depends_on:
      - backend
```

## Run

```bash
docker-compose up -d
```

```

If you want, I can also **add a 1–2 line note** about accessing frontend and backend in the browser so it’s clearer for GitHub users. Do you want me to add that?
```
