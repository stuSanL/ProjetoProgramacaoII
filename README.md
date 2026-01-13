# Projeto de Programação II: GPC - Gestão de Posto de Combustíveis

## 📌 Descrição

Este projeto é uma aplicação **CRUD (Create, Read, Update, Delete)** desenvolvida em **Java**, com persistência de dados realizada por meio de **arquivos de texto**, sem utilização de banco de dados relacional.

O sistema simula a gestão de uma aplicação comercial, lidando com entidades como **clientes, funcionários, combustíveis, tanques e vendas**, sendo voltado principalmente para fins **acadêmicos** e de **aprendizado de arquitetura em camadas**.

---

## ⚙️ Funcionalidades

* ➕ Cadastro de registros
* 📄 Listagem de dados persistidos
* ✏️ Atualização de registros existentes
* ❌ Remoção de registros
* 💾 Persistência em arquivos de texto organizados por entidade
* 🧱 Arquitetura em camadas (Controller, Service, DAO, Model)
* 🖥️ Interface via terminal (CLI)

---

## 🗂️ Persistência de Dados

A persistência é feita utilizando **arquivos de texto**, organizados por tipo de entidade no diretório `data/`.

* Cada entidade possui seu próprio diretório
* Os arquivos seguem um formato padronizado para leitura e escrita
* IDs são controlados separadamente para garantir unicidade

```
data/
├── clientes/
├── combustiveis/
├── funcionarios/
├── tanques/
├── vendas/
└── ids/
```

> ⚠️ Atenção: a edição manual dos arquivos pode causar inconsistências caso o formato não seja respeitado.

---

## 🧱 Arquitetura do Projeto

O projeto segue uma organização em camadas para facilitar manutenção e entendimento:

```
src/
└── com/
    ├── controller/   # Controle do fluxo da aplicação
    ├── dao/          # Acesso e persistência em arquivos
    ├── exception/    # Exceções customizadas
    ├── model/        # Entidades do sistema
    ├── service/      # Regras de negócio
    ├── test/         # Testes
    ├── ui/           # Interface CLI
    ├── Main.java            # Ponto de entrada da aplicação
    └── Paths.java           # Centralização dos caminhos dos arquivos com enum
```

---

## 🛠️ Tecnologias Utilizadas

* **Java**
* Manipulação de arquivos (`File`, `BufferedReader`, `BufferedWriter`, etc.)
* Execução em **linha de comando (CLI)**

---

## ▶️ Como Executar

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
```

2. Compile o projeto:

```bash
javac -d out src/**/*.java
```

3. Execute a aplicação:

```bash
java -cp out Main
```

---

## 🎯 Objetivo Acadêmico

Este projeto tem como principais objetivos:

* Praticar **CRUD em Java**
* Trabalhar com **persistência em arquivos**
* Aplicar **separação de responsabilidades**
* Exercitar **tratamento de exceções** e **organização de código**

---

## 📄 Licença

Projeto desenvolvido para fins educacionais. Uso livre para estudo e adaptações.
