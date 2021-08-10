# Dica Importante

* Para a versão do tomcat apache-tomcat-8.5.23 é necessário colocar 3 libs no caminho 

```
/apache-tomcat-8.5.23/lib
```

* Lib's

```
c3p0-0.9.5.2.jar

mchange-commons-java-0.2.11.jar

postgresql-42.2.1.jar
```
 
Essas lib's são responsáveis por controlar a conexão no banco de dado junto com o tomcat

* Também clique duas vezes no tomcat em seu eclipse e na aba timeout aumente o valor de 45 para 125, como demora inicializar com o c3p0 ele pode retornar um erro caso fique em 45.


# Entendendo o mapeamento do JPA

[Tipos de Heranca no Hibernate](http://www.devmedia.com.br/tipos-de-heranca-no-hibernate/28641) - Artigo do devmedia, estamos utilizando herança por "Tabela por Subclasse"

**Para ficar mais claro como é o mapeamento das classes realizando herança entre pessoa classe pai, cliente e funcionario classe filho, segue abaixo o link;**


## Mapeamento com colunas iguais

```java
@Entity
@Table(name="cor")
public class Cor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cor")
	private Long id;

	@NotBlank
	private String descricao;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
}

```

Descrição não tem o qualificador **Column(name = "descricao")** porque o mesmo nome que está na classe é o mesmo que está na tabela **cor** no banco de dados;

Segue abaixo o padrão utilizado no banco de dados para comandos DDL direcionados as tabelas e atributos;

1 - Todos dos atributos e nomes de tabelas tem que ser em minúsculo 

2 – Todos os atributos com duas palavras têm que ser separado por **Underscore (_)** 

3 – Cadas tabela é identificada por **id_tabela** mas na classe java ele é identificado com **id**, ou seja, todas as classes têm um id mas com um **Column(name=”id_tabela”)**


# Consultas com o jpaRepository do Spring Data JPA

[4.3.2. Query creation](https://docs.spring.io/spring-data/jpa/docs/2.0.0.RELEASE/reference/html/#jpa.query-methods.query-creation) - Documetação do Spring Data JPA

**Exemplo**

```java
public interface Cores extends JpaRepository<Cor, Long>{	

	public Optional<Cor> findByDescricaoIgnoreCase(String descricao);
	
}
```

## Table 4. Supported keywords inside method names

| Keyword | Sample | JPQL snippet |
| ------ | ------ | ------------ |
| And | findByLastnameAndFirstname | … where x.lastname = ?1 and x.firstname = ?2 |
| Or | findByLastnameOrFirstname | … where x.lastname = ?1 or x.firstname = ?2 |
| Is,Equals | findByFirstname,findByFirstnameIs,findByFirstnameEquals | … where x.firstname = ?1 |
| Between | findByStartDateBetween | … where x.startDate between ?1 and ?2 |
| LessThan | findByAgeLessThan | … where x.age &lt; ?1 | 
| LessThanEqual| findByAgeLessThanEqual| … where x.age &lt;= ?1 |
| GreaterThan | findByAgeGreaterThan | … where x.age &gt; ?1 |
| GreaterThanEqual | findByAgeGreaterThanEqual | … where x.age &gt;= ?1 |
| After | findByStartDateAfter | … where x.startDate &gt; ?1 |
| Before | findByStartDateBefore | … where x.startDate &lt; ?1 |
| IsNull | findByAgeIsNull | … where x.age is null |
| IsNotNull,NotNull | findByAge(Is)NotNull | … where x.age not null |
| Like | findByFirstnameLike | … where x.firstname like ?1 |
| NotLike | findByFirstnameNotLike | … where x.firstname not like ?1 |
| StartingWith | findByFirstnameStartingWith | … where x.firstname like ?1 (parameter bound with appended %) |
| EndingWith | findByFirstnameEndingWith | … where x.firstname like ?1 (parameter bound with prepended %) |
| Containing | findByFirstnameContaining | … where x.firstname like ?1 (parameter bound wrapped in %) |
| OrderBy | findByAgeOrderByLastnameDesc | … where x.age = ?1 order by x.lastname desc |
| Not | findByLastnameNot | … where x.lastname &lt;&gt; ?1 |
| In | findByAgeIn(Collection&lt;Age&gt; ages) | … where x.age in ?1 |
| NotIn | findByAgeNotIn(Collection&lt;Age&gt; ages) | … where x.age not in ?1 |
| True | findByActiveTrue() | … where x.active = true |
| False | findByActiveFalse() | … where x.active = false |
| IgnoreCase | findByFirstnameIgnoreCase | … where UPPER(x.firstame) = UPPER(?1) |


# Trabalhando com JavaScript

[Addy Osmani](https://addyosmani.com/) - Engineering manager at Google working on Chrome

[Learning JavaScript Design Patterns](https://addyosmani.com/resources/essentialjsdesignpatterns/book/index.html) - A book by Addy Osmani Volume 1.7.0

![Screenshot](Documentacao/javascript.jpg) 

### Exemplo de js estruturado by Alan Passos

```js
var Teste = Teste || {};

/* PRIMEIRA INSTANCIA*/
Teste.Iniciar = (function() {

    /*CONSTRUTOR*/
    function Iniciar() {
        this.countIniciar = 0;
        console.log("Valor Inicial: " + this.countIniciar);
    }

    /*INICIALIZANDO A INSTANCIA COM PROTOTYPE*/
    Iniciar.prototype.getInstance = function() {
        this.countIniciar++;
        console.log("Instancia Inicial: " + this.countIniciar);
    }

    return Iniciar;

}());

/*SEGUNDA INSTANCIA*/
Teste.Finalizar = (function() {

    /*CONSTRUTOR*/
    function Finalizar(iniciar) {
        this.iniciar = iniciar;
        /*PEGANDO O CONTADOR DE INICIAR A INCREMENTANDO PARA IMPIRMIR*/
        this.iniciar.countIniciar++;
        console.log("Valor Final: " + this.iniciar.countIniciar);
    }

    /*INICIALIZANDO A INSTANCIA COM PROTOTYPE*/
    Finalizar.prototype.getInstance = function() {
        printValor.call(this);
    }

    function printValor() {
        console.log("Total: " + this.iniciar.countIniciar);
    }

    return Finalizar;

}());



(function() {
    /*INICIALIZANDO INSTANCIA INICIAR*/
    var iniciar = new Teste.Iniciar();
    iniciar.getInstance();

    /* INICIALIZANDO INSTANCIA FINALIZAR PASSANDO COMO PARAMETRO O INICIAR*/
    var finalizar = new Teste.Finalizar(iniciar);
    finalizar.getInstance();
})();
```


webapps/ROOT/index.jsp
adicionar essa linha antes de tudo 
```xml
<% response.sendRedirect("/fenrir"); %>
```
