


## :Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?
R: Primeiramente, um software precisa ser confiável para o usuário final. Bugs e erros não mapeados podem e vão ocorrer em qualquer aplicação madura, porém, esta situação não pode virar regra, o usuário não pode utilizar a aplicação com o sentimento de que a qualquer momento alguma operação dará errado. Essa aplicação também precisa seguir um padrão de UI/UX que permita uma usabilidade fluída e intuitiva.Por fim, manutenção. Esse fator é imprescindível para qualquer equipe que trabalha com esse software. Um código fonte que não é passível de manutenção condena a escalabilidade da aplicação. Eu alcançaria todos esses critérios através de uma boa documentação, um bom mapeamento dos comportamentos das classes, e um refactor constante dos componentes do projeto.

## :Em qual etapa da implementação você considera a qualidade de software?
R: Desde sua concepção conceitual. Pessoalmente, acredito que softwares são gerados a partir da necessidade de se resolver problemas encontrados no cotidiano. Se um recurso que vale um bom esforço é implementado sem analisar as causas que levaram a sua concepção, você estará executando com perfeição todas as etapas de um plano que não leva a lugar algum. Uma implementação que não tem motivo plausível para existir não tem qualidade.


<h1 align="center">Começando a Utilizar</h1>

## :memo: Descrição
Esse projeto ilustra a estrutura da API gerada através da utilização do framework Spring Boot intregado ao banco de dados H2  utilizando o JDK19.0.1


## :wrench: Tecnologias utilizadas
* JDK 19.0.1: Versão utiliza do Java
* Package Type: Jar
* Extensões instaladas no Visual Studio Code: Spring Web, Thymeleaf, Spring Boot DevTools, Spring Data JPA, H2 Database
* [IDE Utilizada: Visual Studio Code](https://code.visualstudio.com/)
* [Spring Boot 3.0.1: Versão Utilizada do Spring Boot](https://spring.io/projects/spring-boot)


## :books: Rodando no Ambiente IntelliJ IDEA
* Para Utilizar este projeto através da IDE IntelliJ, primeiramente descompacte o projeto dentro do ambiente.
* Em seguida, na opção "Select Run Configuration" no menu de opções do lado direto, selecione "BackEndProjectApplication" e logo após, selecione a opção/ícone "Run"
  (Disponível após a build da aplicação).
* Para rodar os testes, clique com o botão direito no projeto e logo após na opção "Run All Tests".
* Caso você não tenha o JDK19 instalado, o IntelliJ recomendará automaticamente a instalação.
* As rotas da API estão disponíveis em http://localhost:8080/swagger-ui/index.html
* As configurações do banco estarão disponíveis no arquivo "aplication.properties"



## :books: Rodando no Ambiente Visual Studio Code
* Para Utilizar este projeto através da IDE Visual Studio Code, primeiramente descompacte o projeto dentro do ambiente.
* Vá na aba "Extensions" do VSCODE e instale a extensão "Spring boot Dashboard" para habilitar os comandos do spring, logo em seguida reinicie a IDE
* Espere o projeto ser buildado pela IDE, assim que ele termina estará disponível no dashboard do spring a opção "Run"
* Caso não possua o JDK19, baixe a versão através do link disponibilizado na seção "Tecnologias Utilizadas"
* As rotas da API estão disponíveis em http://localhost:8080/swagger-ui/index.html
* As configurações do banco estarão disponíveis no arquivo "aplication.properties"





Status do projeto
## :dart: 
Em andamento. 