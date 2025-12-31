import { useState } from "react";
import { createAccount } from "../api/accountApi";

export default function CreateAccountForm({ onAccountCreated }) {
  const [name, setName] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!name) return;

    try {
      setLoading(true);
      await createAccount(name);
      setName("");
      onAccountCreated();
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Account Name"
      />
      <button type="submit" disabled={loading}>
        {loading ? "Creating..." : "Create Account"}
      </button>
    </form>
  );
}
