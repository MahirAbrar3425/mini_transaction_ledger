import { useEffect, useState } from "react";
import { fetchTransactions } from "../api/transactionApi";

export default function LedgerView({ accountId, reloadFlag }) {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);

  const load = async () => {
    setLoading(true);
    const txs = await fetchTransactions(accountId);
    setTransactions(txs);
    setLoading(false);
  };

  useEffect(() => {
    if (accountId) load();
  }, [accountId, reloadFlag]);

  if (loading) return <p>Loading...</p>;

  let balance = 0;

  return (
    <div className="p-4 bg-gray-50 rounded shadow">
      <h3 className="text-lg font-semibold mb-3">Transactions</h3>
      <ul className="space-y-2">
        {transactions.map((tx) => {
          balance += tx.type === "CREDIT" ? tx.amount : -tx.amount;
          return (
            <li
              key={tx.id}
              className="flex justify-between p-2 border rounded bg-white shadow-sm"
            >
              <span>{tx.type}</span>
              <span>{tx.amount}</span>
              <span>{tx.description}</span>
              <span>Bal: {balance}</span>
            </li>
          );
        })}
      </ul>
    </div>
  );
}
