📌 Planejamento de Desenvolvimento — Conecta PR (MVP AEP)
🏁 Sprint 1 — Base do Projeto e Autenticação

Objetivo: levantar o alicerce do sistema e garantir autenticação segura.

O que implementar

Configuração inicial:

Projeto Spring Boot (Java 21). - FEITO

Construção de um CRUD Genérico - FEITO 

Integração com PostgreSQL via JPA + Flyway (migrações). 

Documentação automática da API com Swagger/OpenAPI.

Usuário e Perfis:

Entidade Usuario com dados básicos (nome, email, cpf, senha, ativo).

Entidade Perfil para controlar papéis:

ADMIN → gestor estadual.

ORG_REP → representante de organização.

PROFESSOR → docente.

ALUNO → discente.

Relacionamento Usuario-Perfil (um usuário pode ter mais de um perfil).

Autenticação JWT:

Login (/auth/login) com email + senha (BCrypt).

Geração de tokens de acesso + refresh.

Endpoint /auth/me para retornar dados do usuário logado.

Proteção de rotas via roles (ex.: @PreAuthorize).

🏗️ Sprint 2 — Cadastros Básicos

Objetivo: estruturar entidades que darão suporte ao fluxo principal.

O que implementar

Organizações (/organizacoes)

Entidade Organizacao com razão social, CNPJ, tipo (empresa, ONG, órgão público), site.

CRUD com validações (CNPJ único, campos obrigatórios).

Cursos (/cursos)

Entidade Curso (nome, código).

Usado para vincular desafios às áreas de formação.

Áreas de Conhecimento (/areas)

Entidade AreaConhecimento (ex.: TI, saúde, educação).

Serve como tag para desafios e propostas.

Benefício

Esses cadastros fornecem a base para vincular desafios a cursos/áreas e conectar empresas (organizações) com academia.

🚀 Sprint 3 — Desafios (Problemas do Mercado)

Objetivo: permitir que empresas ou gestores publiquem problemas reais para alunos/professores resolverem.

O que implementar

Entidade Desafio

Campos: título, resumo, detalhes, prazo de submissão, status (RASCUNHO | PUBLICADO | ENCERRADO), cursos e áreas alvo.

Criado por ORG_REP (empresa) ou ADMIN (estado).

Endpoints

CRUD /desafios.

Publicar/encerrar desafio.

Listagem com filtros (status, curso, área, palavra-chave).

Regras de negócio

Somente dono/ADMIN edita.

Propostas só podem ser enviadas enquanto o desafio está PUBLICADO e dentro do prazo.

📩 Sprint 4 — Propostas (Respostas Acadêmicas)

Objetivo: permitir que professores/alunos respondam aos desafios.

O que implementar

Entidade Proposta

Ligada a Desafio.

Contém professor responsável, alunos participantes, título e resumo.

Status: RASCUNHO | ENVIADA | CANCELADA | RECUSADA | ACEITA.

Fluxos

Professor/Aluno cria proposta em rascunho.

Professor envia (ENVIADA).

Empresa avalia → aceita ou recusa (com justificativa).

Endpoints

CRUD /propostas.

POST /propostas/{id}/enviar.

POST /propostas/{id}/avaliar (aceitar/recusar).

Regras de negócio

Não é possível enviar após o prazo do desafio.

Aceite gera automaticamente um Projeto (Sprint 5).

🛠️ Sprint 5 — Projetos (Execução)

Objetivo: acompanhar o andamento das propostas aceitas.

O que implementar

Entidade Projeto

Criado automaticamente quando proposta é aceita.

Campos: título, datas previstas, status (ATIVO | CONCLUIDO | CANCELADO).

Entregáveis (/entregaveis)

Produtos/resultados do projeto, com título, descrição, datas e status (PENDENTE | ENTREGUE).

Comentários (/comentarios)

Associados a entregáveis/projetos.

Usados para feedback de empresa e equipe.

Anexos (/anexos)

Upload de arquivos (S3/MinIO ou sistema local).

Ligados a entregáveis ou projetos.

Regras de negócio

Equipe de projeto pode registrar entregáveis.

Empresas podem comentar e anexar feedback.

🔔 Sprint 6 — Notificações e Painel

Objetivo: dar visibilidade e controle sobre eventos do sistema.

Notificações

Entidade Notificacao: id, usuário, tipo, mensagem, lida/não lida.

Disparadas em eventos-chave:

Nova proposta recebida.

Proposta aceita/recusada.

Novo entregável criado.

Endpoints:

GET /notificacoes (listar, filtrar não lidas).

POST /notificacoes/{id}/marcar-lida.

Painel (dashboards simples)

Endpoint /painel/geral retorna:

Contagem de desafios por status.

Contagem de propostas por status.

Contagem de projetos por status.

Top áreas/cursos com mais desafios.

📜 Sprint 7 — LGPD e Auditoria

Objetivo: garantir rastreabilidade e conformidade mínima com dados pessoais.

O que implementar

Termo de Consentimento

Usuário aceita no primeiro login.

Endpoint para registrar aceite (/termos/consentimento).

Exportação de Dados

GET /me/dados → exporta dados pessoais do usuário (JSON).

Auditoria

Registrar operações críticas (quem, quando, o que).


Entidade AuditoriaEvento (antes/depois em JSON).

