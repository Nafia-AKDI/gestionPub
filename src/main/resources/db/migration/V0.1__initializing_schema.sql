CREATE TABLE public.emplacements
(
    id     bigserial NOT NULL,
    nom    VARCHAR(255),
    type   VARCHAR(20),
    format VARCHAR(20),
    prix   DECIMAL(10, 2),
    CONSTRAINT emplacement_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users
(
    id             bigserial NOT NULL,
    nom            VARCHAR(255),
    "email"        VARCHAR(255),
    "mot_de_passe" VARCHAR(255),
    CONSTRAINT user_pkey PRIMARY KEY (id)
);



CREATE TABLE public.cibles
(
    id           bigserial NOT NULL,
    age          VARCHAR(20),
    sexe         VARCHAR(20),
    localisation VARCHAR(255),
    CONSTRAINT cible_pkey PRIMARY KEY (id)
);

CREATE TABLE public.campagnes
(
    id             bigserial NOT NULL,
    nom            VARCHAR(255),
    date_debut     DATE,
    date_fin       DATE,
    budget         DECIMAL(10, 2),
    objectif       VARCHAR(20),
    user_id        int8,
    emplacement_id int8,
    CONSTRAINT campagne_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT fk_emplacement FOREIGN KEY (emplacement_id) REFERENCES public.emplacements (id)
);


CREATE TABLE public.annonces
(
    id          bigserial NOT NULL,
    titre       VARCHAR(255),
    description VARCHAR(555),
    dateDebut   DATE,
    dateFin     DATE,
    lienImage   VARCHAR(255),
    campagne_id int8,
    CONSTRAINT annonce_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT fk_campagne FOREIGN KEY (campagne_id) REFERENCES public.campagnes (id)
);

CREATE TABLE annonces_cibles
(
    cible_id   INTEGER REFERENCES cibles (id),
    annonce_id INTEGER REFERENCES annonces (id),

    CONSTRAINT annonces_cibles_pkey PRIMARY KEY (cible_id, annonce_id)

);

