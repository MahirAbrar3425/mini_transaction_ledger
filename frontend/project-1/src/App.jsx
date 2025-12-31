import { useState } from "react";
import LoginOrCreateAccount from "./components/LoginOrCreateAccount";
import TransactionForm from "./components/TransactionForm";
import LedgerView from "./components/LedgerView";
import { fetchAccount } from "./api/accountApi";

function App() {
  const [account, setAccount] = useState(null);
  const [reloadTx, setReloadTx] = useState(false);

  const loadAccount = async (acc) => {
    const fresh = await fetchAccount(acc.id);
    setAccount(fresh);
  };

  const handleTransactionCreated = async () => {
    if (!account) return;
    const fresh = await fetchAccount(account.id); // 🔑 must use account.id
    setAccount(fresh);           // update balance
    setReloadTx(prev => !prev);  // trigger LedgerView reload
  };

  return (
    <div className="p-6 max-w-2xl mx-auto">
      <h1 className="text-3xl font-bold mb-6 text-center">Transaction Ledger</h1>

      {!account && (
        <LoginOrCreateAccount onAccountLoaded={loadAccount} />
      )}

      {account && (
        <>
          <div className="mb-4 p-4 bg-gray-100 rounded shadow flex justify-between">
            <span className="font-semibold text-lg">{account.name}</span>
            <span className="font-semibold text-lg">Balance: {account.balance}</span>
          </div>

          <TransactionForm
            accountId={account.id}
            onTransactionCreated={handleTransactionCreated}
          />

          <LedgerView accountId={account.id} reloadFlag={reloadTx} />
        </>
      )}
    </div>
  );
}

export default App;
