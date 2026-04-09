OBS: Ao fazer o commit e push pela primeira vez do documento README.md na máquina da faculdade a assinatura ficou equivocada, por estar logada na conta de outra aluna.

S – Single Responsibility Principle (SRP)

A classe "App" anteriormente estava sendo responsável por praticamente todo o funcionamento do sistema, como a entrada de dados, as regras de negócios e o armazenamento dos dados. O SRP foi utilizado ao retirar dessa classe todas essas responsibilidades e distribuí-las nas camadas: controllers, repositories e services.

Os repositories foram criados para isolar a responsabilidade de armazenamento e acesso aos dados e, cada entidade passou a possuir seu próprio repository. Os controllers, foram criados para controlar a interação com o usuário, como a entrada e saída de dados. Portanto, os métodos aplicarProva(), cadastrar(), escolherParticipante(), escolherProva() e outros que fazem parte da interação direta com o usuário foram transferidos para os controllers de cada entidade. Já os services, foram criados para concentrar as regras de negócios como validações ou o cálculo da nota, por exemplo. Por fim, a criação do Menu isolou completamente a lógica de navegação da aplicação, reduzindo ainda mais a responsabilidade da classe principal "App".


O – Open/Closed Principle (OCP)

O Open/Closed Principle foi utilizado ao criar uma classe genérica BaseRepository com a criação de repositórios separados para cada entidade, pois agora, caso seja necessário criar novos repositories de novas entidades, não é necessário modificar o código da classe já existente, apenas estender.


L – Liskov Substitution Principle (LSP)

O princípio da substituição de Liskov (LSP) foi utilizado ao garantir que qualquer implementação de repositório possa ser utilizada no lugar de outra sem quebrar o sistema, desde que respeite os mesmos métodos e comportamentos esperados. Para isso foi criada a interface Categoria, que garante a presença de um ID em todas as entidades que a implementam. Logo, o BaseRepository (que estende a interface) utiliza esse contrato (obrigatoriedade do ID) para implementar as operações de forma genérica, o que vai permitir que diferentes entidades que sigam esse contrato sejam tratadas de maneira uniforme. Logo, os repositories específicos de cada entidade vão herdar esses comportamentos sem modificá-los, portanto sem impactar o sistema.


I – Interface Segregation Principle (ISP)

O Interface Segregation Principle foi utilizado ao criar a interface Categoria de maneira bem específica, definindo apenas os métodos essenciais. Portanto, nenhuma classe é obrigada a implementar métodos que não utilizam. Por exemplo, as classes específicas dos repositories só irão herdar métodos que são necessários à todas, como salvar(), listar()e buscarPorId().


D – Dependency Inversion Principle (DIP)

O princípio da inversão de dependência (DIP) foi utilizado ao fazer com que as classes recebam suas dependências por meio de injeção, em vez de instanciá-las diretamente. Como por exemplo, no App, os repositories são criados e passados como parâmetro para os services, e estes para os controllers, visto que as classes services e controllers, não criam repositories, apenas recebem. Portanto, as classes deixam de criar suas próprias dependências, reduzindo o acoplamento.


