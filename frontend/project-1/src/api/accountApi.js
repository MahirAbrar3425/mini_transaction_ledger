import apiClient from "./axios";

// Create a new account
export const createAccount = async (name) => {
  const res = await apiClient.post("/accounts", { name });
  return res.data; // { id, name, balance }
};

// Login / fetch account by name (QUERY PARAM)
export const loginByName = async (name) => {
  const res = await apiClient.get("/accounts/by-name", {
    params: { name },
  });
  return res.data; // { id, name, balance }
};

// Fetch account by ID
export const fetchAccount = async (accountId) => {
  const res = await apiClient.get(`/accounts/${accountId}`);
  return res.data; // { id, name, balance }
};
