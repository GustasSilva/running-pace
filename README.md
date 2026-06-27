# 🏎️ Running Pace

Aplicação desktop em **Java (Swing)** com tema de automobilismo. O usuário cadastra
um piloto (nome, equipe e número do carro), os dados são salvos em um banco
**MySQL**, e em seguida é exibido um **cronômetro** com marcação de voltas.

O app abre com uma tela de **splash** (logo + trilha sonora) e, ao pressionar
qualquer tecla, segue para o cadastro.

> Projeto acadêmico.

---

## ✨ Funcionalidades

- 🎬 Tela inicial (splash) com imagem de fundo e áudio
- 📝 Cadastro de piloto / equipe / número do carro
- 💾 Persistência dos dados em **MySQL** (tabela `placar`)
- ⏱️ Cronômetro com minutos, segundos, milissegundos e voltas
- 🖼️ Interface com imagens de fundo personalizadas

---

## 🛠️ Tecnologias

- **Java** (Swing / AWT) — interface gráfica
- **MySQL** + **JDBC** (`mysql-connector-j-8.4.0.jar`, incluído em `lib/`)
- **javax.sound.sampled** — reprodução de áudio

---

## 🚀 Como executar

### Pré-requisitos
- **JDK 17+** (testado com JDK 17)
- **MySQL** em execução em `localhost:3306`

### 1. Preparar o banco de dados
Crie o banco e a tabela executando o script incluído:

```bash
mysql -u root < db/schema.sql
```

> As credenciais usadas pelo app são `root` sem senha (padrão de desenvolvimento
> local). Ajuste em `src/aplicativo/cadastro.java` (método `conecta()`) se o seu
> MySQL usar outro usuário/senha.

### 2. Compilar
A partir da raiz do projeto:

```bash
# Windows
javac -encoding UTF-8 -cp "lib/*" -d bin src/aplicativo/*.java

# Linux/macOS
javac -encoding UTF-8 -cp "lib/*" -d bin src/aplicativo/*.java
```

### 3. Executar
É importante rodar **a partir da raiz do projeto**, pois os caminhos de imagem e
áudio são relativos (`img/...`, `audio/...`):

```bash
# Windows
java -cp "bin;lib/*" aplicativo.launcher

# Linux/macOS
java -cp "bin:lib/*" aplicativo.launcher
```

---

## 📁 Estrutura do projeto

```
running-pace/
├── src/aplicativo/
│   ├── launcher.java       # Tela inicial (splash + áudio) → abre o cadastro
│   ├── cadastro.java       # Cadastro + conexão MySQL + cronômetro (classe interna)
│   └── salvar_dados.java   # Classe-modelo (piloto, equipe, número do carro)
├── lib/
│   └── mysql-connector-j-8.4.0.jar
├── img/                    # Imagens usadas na interface
├── audio/                  # Trilha sonora da tela inicial
├── db/
│   └── schema.sql          # Criação do banco e da tabela `placar`
├── .vscode/                # Configuração do projeto Java no VS Code
└── README.md
```

> A pasta `bin/` (classes compiladas) não é versionada — veja `.gitignore`.
