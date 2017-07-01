# Intelipost - Case técnico

## Descrição

Segunda solução para o case técnico apresentado pela Intelipost. 

Seguindo a linha das tecnologias e frameworks utilizados na Intelipost, foi feita uma implementação de referência utilizando:

* Java 8
* Gradle
* Spring boot
* Thymeleaf
* Spring JDBC Template
* Spring Cache (implementação Caffeine)

## Performance

### Acesso ao banco de dados

Mediante ao requisito de muitos acessos na aplicação, a simplicidade foi levada em conta para evitar overheads, e por este motivo, não foi utilizada uma camada de persistência como hibernate ou outra para abstração do banco de dados, sendo utilizado acesso direto ao banco de dados com o JDBCTemplate.

### Autenticação

Pelo mesmo motivo descrito acima, foi utilizado uma autenticação simples, através de usuário e senha, evitando um overhead de um sistema completo de autenticação como Spring Security, Shiro ou algum outro.

### Montagem de dados personalizados

Os dados personalizados (descritos com mais detalhes abaixo) são armazenados em um cache usando a propria especificação de cache (anotações `@Cacheable`) do Spring. A implementação escolhida foi a `Caffeine` pela simplicidade, mas dependendo da necessidade da aplicação e do ambiente, pode ser alternada a implementação para uma que use `redis`, `memcached`, `ehcache`, ou outra existente.

### Acesso a arquivos estáticos

Para evitar o custo do servidor web estar respondendo a arquivos estáticos, os assets (bootstrap, jquery, etc) estão sendo usados diretamente de CDNs. 

Os únicos assets da aplicação são 2 folhas de estilo (css) que estão dentro da aplicação, mas não estão sendo servidas pelo servidor, uma vez que o AWS CloudFront está configurado para este domínio com um `Cache Behaviour` específico para o path `domain/stylesheets/*`. Desta maneira, o CloudFront irá servir a partir dos seus edges este conteúdo enquato irá fazer um bypass das requisições que não derem um match neste pattern.

## Acessando a aplicação

### Web

A aplicação está disponível em: [http://djcb07f2873bx.cloudfront.net
](http://djcb07f2873bx.cloudfront.net) para acesso. O acesso web permite login de usuário para acesso a um dashboard. Havia um requisito de personalizar o acesso do usuário, portanto foi criada uma estrutura N:N entre usuários e perfis de acesso e outra N:N entre os perfis e links que estes perfis possam acessar.

Após o login do usuário, será montada uma estrutura de menu lateral com os links que o usuário possui acesso baseado em seus perfis (nenhum destes links possui ação implementada). 

Para teste, a aplicação pode ser acessada com algumas das credenciais abaixo, verificando a alteração dos items disponíveis no menu lateral.

    perfil: admin
    usuario: admin@email.com
    senha: 123

    perfil: marketing
    usuario: mkt@email.com
    senha: 123

    perfil: vendas
    usuario: sales@email.com
    senha: 123

    perfil: TI
    usuario: tech@email.com
    senha: 123

#### Autenticação para Web

A autenticação dos requests na aplicação pode ser feita de duas maneiras, ou guardando o token do usuário logado na sessão (http session) ou em cookies. Foram criadas as implementações para as duas condições e para definir qual é a estratégia de sessão, basta alterar o valor da propriedade `ip.session.strategy` no arquivo `application-[env].properties` para o valor desejado:

* `http` para Http Session
* `cookie` para Cookies

### REST

Como requisito também foi colocado que outra aplicação da empresa deveria acessar os dados dos usuários através desta aplicação, por este motivo foi disponibilizado um acesso REST a 3 funções dentro desta aplicação. 

* Listagem de usuários
* Detalhes de um usuário
* Listagem dos perfis de um usuário

Os endpoints foram testados com o Postman e foram descritos e documentados usando a própria ferramenta de docs dele. Está publicado em: [https://documenter.getpostman.com/view/206471/intelipost/6fVV4p1](https://documenter.getpostman.com/view/206471/intelipost/6fVV4p1)

#### Autenticação para REST

Para usar os endpoints REST, o request deve possuir um header `token`, e um interceptor irá liberar o acesso caso o header exista (não é feita validação de integridade deste token, como explicado na classe.método `UserTokenService.checkIfTokenIsValid`. 

Token de exemplo: `bbbeb10d-f635-4855-adb4-281484664537`.

## Rodando a aplicação

O código pode ser baixado e rodado diretamente com o gradle usando

`$ ./gradlew bootRun`

As credenciais de acesso ao banco em desenvolvimento são configuradas no arquivo `application-default.properties` e para produção no arquivo `application-production.properties`. O arquivo inicial do banco de dados `init.sql` possui as tabelas e inserts necessários para a aplicação rodar.

### Em produção

Para fazer o build da aplicação, basta rodar o comando

`$ ./gradlew assemble`

E será construído um jar em `build/libs` que poderá ser executado em normalmente como outro jar qualquer. Em produção deve-se especificar o ambiente para que as credenciais de acesso ao banco de dados sejam relidas corretamente usando:

`$ java -jar second-0.0.1-SNAPSHOT.jar  --spring.profiles.active=production`

Em um ambiente de produção, ainda é necessário configurar a JVM com os parâmetros que possam usar o máximo dos recursos disponíveis.
