import { useState } from "react";
import { createTransaction } from "../api/transactionApi";

export default function TransactionForm({ accountId, onTransactionCreated }) {
  const [amount, setAmount] = useState("");
  const [type, setType] = useState("CREDIT");
  const [description, setDescription] = useState("");
  const [loading, setLoading] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    if (!amount || !description) return;

    try {
      setLoading(true);
      await createTransaction(accountId, {
        amount: Number(amount),
        type,
        description,
      });
      setAmount("");
      setDescription("");
      onTransactionCreated(); // 🔑 trigger parent refresh
    } finally {
      setLoading(false);
    }
  };

  return (
    <form
      onSubmit={submit}
      className="mb-6 p-4 bg-white rounded shadow flex flex-col gap-3"
    >
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        className="border p-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />

      <select
        value={type}
        onChange={(e) => setType(e.target.value)}
        className="border p-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        <option value="CREDIT">CREDIT</option>
        <option value="DEBIT">DEBIT</option>
      </select>

      <input
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        className="border p-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />

      <button
        type="submit"
        disabled={loading}
        className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600 disabled:opacity-50"
      >
        {loading ? "Adding..." : "Add"}
      </button>
    </form>
  );
}
