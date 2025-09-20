import React, { useEffect, useState } from "react";

interface Personel {
  id?: number;
  ad: string;
  soyad: string;
  email: string;
  pozisyon: string;
}

function App() {
  const [personeller, setPersoneller] = useState<Personel[]>([]);
  const [newPersonel, setNewPersonel] = useState<Personel>({
    ad: "",
    soyad: "",
    email: "",
    pozisyon: "",
  });
  const [editPersonel, setEditPersonel] = useState<Personel | null>(null);

  const API_URL = "http://localhost:8080/personel";

  useEffect(() => {
    fetch(API_URL)
      .then((res) => res.json())
      .then((data) => setPersoneller(data))
      .catch((err) => console.error("Fetch error:", err));
  }, []);

  const addPersonel = () => {
    fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newPersonel),
    })
      .then((res) => res.json())
      .then((data) => {
        setPersoneller([...personeller, data]);
        setNewPersonel({ ad: "", soyad: "", email: "", pozisyon: "" });
      });
  };

  const deletePersonel = (id?: number) => {
    if (!id) return;
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
      .then(() => {
        setPersoneller(personeller.filter((p) => p.id !== id));
      });
  };

  const startEdit = (p: Personel) => {
    setEditPersonel({ ...p });
  };

  const saveEdit = () => {
    if (!editPersonel?.id) return;

    fetch(`${API_URL}/${editPersonel.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(editPersonel),
    })
      .then((res) => res.json())
      .then((data) => {
        setPersoneller(
          personeller.map((p) => (p.id === data.id ? data : p))
        );
        setEditPersonel(null);
      });
  };

  return (
    <div>
      <h1>Personel Listesi</h1>
      <ul>
        {personeller.map((p) => (
          <li key={p.id}>
            {p.ad} {p.soyad} - {p.email} ({p.pozisyon})
            <button onClick={() => deletePersonel(p.id)}>Sil</button>
            <button onClick={() => startEdit(p)}>Güncelle</button>
          </li>
        ))}
      </ul>

      {/* Add form */}
      <h2>Yeni Personel Ekle</h2>
      <input
        type="text"
        placeholder="Ad"
        value={newPersonel.ad}
        onChange={(e) => setNewPersonel({ ...newPersonel, ad: e.target.value })}
      />
      <input
        type="text"
        placeholder="Soyad"
        value={newPersonel.soyad}
        onChange={(e) =>
          setNewPersonel({ ...newPersonel, soyad: e.target.value })
        }
      />
      <input
        type="email"
        placeholder="Email"
        value={newPersonel.email}
        onChange={(e) =>
          setNewPersonel({ ...newPersonel, email: e.target.value })
        }
      />
      <input
        type="text"
        placeholder="Pozisyon"
        value={newPersonel.pozisyon}
        onChange={(e) =>
          setNewPersonel({ ...newPersonel, pozisyon: e.target.value })
        }
      />
      <button onClick={addPersonel}>Ekle</button>

      {/* Edit form (only visible when editing) */}
      {editPersonel && (
        <div>
          <h2>Personel Güncelle</h2>
          <input
            type="text"
            value={editPersonel.ad}
            onChange={(e) =>
              setEditPersonel({ ...editPersonel, ad: e.target.value })
            }
          />
          <input
            type="text"
            value={editPersonel.soyad}
            onChange={(e) =>
              setEditPersonel({ ...editPersonel, soyad: e.target.value })
            }
          />
          <input
            type="email"
            value={editPersonel.email}
            onChange={(e) =>
              setEditPersonel({ ...editPersonel, email: e.target.value })
            }
          />
          <input
            type="text"
            value={editPersonel.pozisyon}
            onChange={(e) =>
              setEditPersonel({ ...editPersonel, pozisyon: e.target.value })
            }
          />
          <button onClick={saveEdit}>Kaydet</button>
        </div>
      )}
    </div>
  );
}

export default App;
