## Versionamento de código (Git + GitHub)

Para manter o histórico organizado e facilitar o trabalho em equipe, **toda alteração deve seguir o fluxo abaixo**.

### Fluxo padrão de desenvolvimento
1. **Sempre crie uma branch a partir da `main`** (nunca faça commit diretamente na `main`).
2. **Implemente a alteração na sua branch**, fazendo commits pequenos e bem descritos.
3. **Envie a branch para o GitHub** (`push`).
4. **Abra um Pull Request (PR) para a `main`**, descrevendo claramente o que foi feito.
5. **Após aprovação e checagens passarem**, será feito o merge do PR para a `main`.

---

### Padrão de nome de branch
O nome da branch deve seguir obrigatoriamente:

`feature-<número da tarefa>`

Exemplos:
- `feature-123`
- `feature-58`

---

### Commits
Boas práticas:
- Faça **commits pequenos** e frequentes (um commit por mudança coerente).
- Mensagens de commit devem ser claras e objetivas.

Sugestões de padrão:
- `feat: adicionar tela de login (task 123)`
- `fix: corrigir validação de CPF (task 58)`
- `refactor: reorganizar serviço de autenticação (task 77)`

---

### Pull Requests (PRs)
Todo PR deve conter:
- **Título** com o número da tarefa (ex.: `Task 123 - Implementa filtro de usuários`)
- **Descrição** com:
  - o que foi feito
  - como testar
  - prints/logs se fizer sentido

Boas práticas:
- PRs menores são mais fáceis de revisar e menos propensos a conflito.
- Se a mudança for grande, prefira **dividir em PRs menores**.

---

### Regras de segurança e qualidade
- **Proibido** commitar diretamente na `main`.
- **Obrigatório** passar por PR.
- **Obrigatório** manter a branch atualizada com a `main` quando necessário (para reduzir conflitos).

---

### Atualizando sua branch com a `main`
Antes de abrir o PR (ou se sua branch ficou desatualizada), atualize assim:

```bash
git checkout main
git pull origin main
git checkout feature-123
git merge main
