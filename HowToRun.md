Install Docker Desktop.

Pull the images:

docker pull mahirabrar3425/transaction-ledger-backend:latest
docker pull mahirabrar3425/transaction-ledger-frontend:latest


Create a minimal docker-compose.yml on the machine:

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


Run:

docker-compose up -d
