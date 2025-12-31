import apiClient from "./axios";

// Add a transaction
export const createTransaction = async (accountId, transaction) => {
  const res = await apiClient.post(
    `/accounts/${accountId}/transactions`,
    transaction
  );
  return res.data;
};

// Fetch all transactions for an account
export const fetchTransactions = async (accountId) => {
  const res = await apiClient.get(
    `/accounts/${accountId}/transactions`
  );
  return res.data;
};
