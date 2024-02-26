-- Supprimer la contrainte de clé primaire existante
ALTER TABLE annonces_cibles DROP CONSTRAINT annonces_cibles_pkey;

-- Modifier les colonnes pour corriger les références
ALTER TABLE annonces_cibles
DROP COLUMN annonce_id,
    DROP COLUMN cible_id;

ALTER TABLE annonces_cibles
    ADD COLUMN annonce_id INTEGER REFERENCES annonces(id),
    ADD COLUMN cible_id INTEGER REFERENCES cibles(id);

-- Ajouter une nouvelle contrainte de clé primaire
ALTER TABLE annonces_cibles
    ADD CONSTRAINT annonces_cibles_pkey PRIMARY KEY (annonce_id, cible_id);
