import { useState } from "react";
import { createAccount, loginByName } from "../api/accountApi";

export default function LoginOrCreateAccount({ onAccountLoaded }) {
  const [nameInput, setNameInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async () => {
    if (!nameInput.trim()) {
      setError("Name required");
      return;
    }

    try {
      setLoading(true);
      setError("");
      const acc = await loginByName(nameInput.trim());
      onAccountLoaded(acc);
    } catch (err) {
      setError("Account not found. Click Create Account.");
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = async () => {
    if (!nameInput.trim()) {
      setError("Name required");
      return;
    }

    try {
      setLoading(true);
      setError("");
      const acc = await createAccount(nameInput.trim());
      onAccountLoaded(acc);
    } catch (err) {
      console.error(err.response?.data);
      setError(
        err.response?.data?.message || "Failed to create account"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h3>Login or Create Account</h3>

      <input
        placeholder="Enter account name"
        value={nameInput}
        onChange={(e) => setNameInput(e.target.value)}
      />

      <div style={{ marginTop: "10px" }}>
        <button onClick={handleLogin} disabled={loading}>
          Login
        </button>

        <button
          onClick={handleCreate}
          disabled={loading}
          style={{ marginLeft: "8px" }}
        >
          Create Account
        </button>
      </div>

      {error && (
        <div style={{ color: "red", marginTop: "10px" }}>
          {error}
        </div>
      )}
    </div>
  );
}
