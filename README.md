# Clínica VidaPlena — AV2 

Sistema de gestão de uma clínica multidisciplinar, em Java puro (console). É a evolução do projeto base (fork): os arrays fixos deram lugar ao Java Collections Framework e o código foi reestruturado para aplicar encapsulamento, herança, polimorfismo, interfaces, relacionamentos e tratamento de exceções.

> O diagrama de classes está no arquivo **`DIAGRAMA.md`**.

## Tecnologias

- Java (JDK 11 ou superior)
- Apenas `java.util` (Collections, Scanner) e `java.lang` — sem frameworks, bibliotecas externas, banco de dados ou interface gráfica.

## Funcionalidades

**Pacientes**
- Cadastrar (3 formas: mínimo; com idade e telefone; completo com convênio)
- Complementar cadastro (idade/telefone e, opcionalmente, convênio)
- Buscar por CPF
- Listar todos
- Desativar

**Profissionais** (Fisioterapeuta, Psicólogo, Nutricionista, Clínico Geral)
- Cadastrar (a especialidade define o tipo; cada um pede sua informação específica e seus horários)
- Atualizar cadastro (registro, valor e horários)
- Listar todos
- Filtrar por especialidade

**Consultas**
- Agendar escolhendo o profissional
- Agendar por especialidade (o sistema encontra um profissional disponível)
- Cancelar (multa de R$ 50,00 para cancelamento com menos de 2h de antecedência)
- Remarcar
- Listar todas
- Buscar por CPF

**Atendimentos**
- Registrar atendimento (gera o prontuário e anexa automaticamente a informação específica da especialidade)

**Pagamentos** (tratados de forma polimórfica)
- Dinheiro/PIX: 5% de desconto
- Cartão: até 6x; acima de 3x, taxa de 2,5% por parcela extra
- Convênio: aplica a cobertura do convênio (SaudePlus 40%, VidaMais 30%, BemEstar 50%)
- Listar pagamentos

**Relatórios**
- Cadastros (lista unificada de todas as pessoas)
- Consultas (geral)
- Consultas por profissional
- Consultas por período
- Resumo financeiro (total faturado e total em multas)

## Compilação e execução

Na pasta com os arquivos `.java`:

```bash
# compilar (UTF-8 por causa dos acentos nos comentários e nos nomes de especialidade)
javac -encoding UTF-8 *.java

# executar
java Main
```

## Como usar (operações)

O sistema é todo navegado por um menu numérico no console. No menu principal escolha o módulo (1 a 6) e, dentro de cada módulo, a operação desejada. `0` sempre volta ao menu anterior.

Fluxo típico para um teste completo:

1. **Profissionais → Cadastrar**: informe nome, especialidade (`clinica geral`, `fisioterapia`, `psicologia` ou `nutricao`), CPF, telefone, data de nascimento, registro, valor e, ao final, os dias/turnos de atendimento (ex.: `segunda` / `manha`).
2. **Pacientes → Cadastrar**: informe nome e CPF; no tipo completo, idade, telefone e convênio.
3. **Consultas → Agendar**: informe CPF do paciente, nome do profissional, data (`DD/MM/AAAA`) e horário (`HH:MM`). O dia da semana é calculado a partir da data; se o profissional não atende naquele dia ou o horário está ocupado, o sistema avisa.
4. **Atendimentos → Registrar**: informe o índice da consulta (mostrado em *Consultas → Listar*), observações, diagnóstico e procedimentos. A consulta passa para `realizada`.
5. **Pagamentos → Registrar**: informe o valor base e o tipo (`dinheiro`, `pix`, `cartao` ou `convenio`). Para cartão, o número de parcelas; para convênio, o nome do convênio e a especialidade.
6. **Relatórios**: veja os cadastros, as consultas (com filtros) e o resumo financeiro.

Toda leitura de número (idade, valor, índice, parcelas) é protegida: se você digitar um texto inválido, o sistema avisa e pede novamente, sem travar.
