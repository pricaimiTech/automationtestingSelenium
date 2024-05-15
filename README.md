# Projeto de Automação Selenium com Java

Este projeto demonstra um script de automação usando Selenium WebDriver, Java, e Maven. O objetivo é automatizar tarefas web, como testes de registro e validações em um site de exemplo.

## Configuração do Ambiente

Antes de iniciar, você precisará configurar seu ambiente de desenvolvimento com os seguintes componentes:

Passo 1: Configuração do ambiente de desenvolvimento
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html): Certifique-se de ter o JDK instalado.

Após a instalação do JDK, você pode verificar se foi instalado corretamente abrindo um terminal ou prompt de comando e digitando isso deve exibir a versão do Java instalada.

```bash
java -version
``` 
- Configure a variável de ambiente ```JAVA_HOME```

![JAVA_HOME](imgs\JAVA_HOME.png)

Passo 2: Baixar o Chrome WebDriver
- [ChromeDriver](https://googlechromelabs.github.io/chrome-for-testing/#stable): Baixe o ChromeDriver compatível com a versão do navegador Google Chrome no seu sistema (versão utilizada 124.0.6367.91)


Passo 3: Instale o Maven (se ainda não estiver instalado)
- [Maven](https://maven.apache.org/download.cgi): Maven é usado para gerenciar dependências e construir o projeto.
- Extraia o arquivo zip baixado para um diretório de sua escolha no seu sistema.
- Adicione o diretório bin do Maven ao seu PATH do sistema:
```bash
C:\apache-maven-3.8.4\bin
``` 
![JAVA_HOME](imgs\MAVEN.png)
- Abra um novo terminal ou prompt de comando e verifique se o Maven foi instalado corretamente digitando 
```bash
mvn -v
```

Passo 4: Instale a extensão Java Extension Pack em caso de utilizar o VS Code
- Java Extension Pack
- Abra o arquivo `settings.json` (Ctrl + ,) e adicione o bloco de código
```json
{
    "java.home": "<caminho_para_o_JDK>",
    "java.configuration.updateBuildConfiguration": "automatic",
    "java.errors.incompleteClasspath.severity": "ignore",
    "java.errors.missingDependencies.classpathSeverity": "ignore",
    "java.semanticHighlighting.enabled": true,
    "java.test.config": {
        "template": "junit4"
    }
}
```

Passo 5: Crie um novo projeto Maven

- Abra as configurações do VS Code -> Command Palette
- Selecione ```Java: Create Java project``` 
- Escolha a opção ``Maven``
- Escolha a arquitetura ``maven-archetype-quickstart`` 
- Escolha a última versão


Passo 6: Adicione dependências
- [Consultar dependencias Mavens](https://mvnrepository.com/artifact/org.seleniumhq.selenium)
- Abra o arquivo pom.xml no diretório do seu projeto usando um editor de texto ou uma IDE Java.
- Para instalar as depencias utilize o comando 
``` 
mvn clean install
```
- Para resetar o ambiente e instalar novamente, utilize este comando:
```
mvn dependency:purge-local-repository
```

Passo 7: Adicionar o allure report as variaveis de ambiente
https://github.com/allure-framework/allure2/releases 


Passo 8: Rodar os Testes

- Para rodar o teste sem report Allure `RegisterTestt.java`, com a extensão instalada será possível ao clicar com o botão direito no arquivo de teste, escolher a opção `Run Java` 

![RODAR_TESTES](imgs\rodar_testes.png)
    
- Para rodar o teste com report Allure `RegisterTestAllure.java` 

    - instalar a extensão  [TestNg TestSuite Runner](https://marketplace.visualstudio.com/items?itemName=DebrajBhal.testng-testsuite-runner) 
    - rodar o comando da imagem 

![RODAR_TESTES](imgs\execucar_test_allure.png)


Passo 9: Gerar Relatórios do Allure usando a CLI

- Use a linha de comando para gerar um relatório Allure a partir dos dados 
```
allure generate -c -o allure-report allure-results
```
- Depois de gerar o relatório, você pode exibi-lo localmente em um navegador:

```
allure serve allure-results
```

---

### Extensões uteis 

Name: [Allure Support](https://marketplace.visualstudio.com/items?itemName=qameta.allure-vscode)
Name: [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
Name: [Java Language Support](https://marketplace.visualstudio.com/items?itemName=georgewfraser.vscode-javac)
Name: [Language Support for Java(TM) by Red Hat](https://marketplace.visualstudio.com/items?itemName=redhat.java)
Name: [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)
Name: [Project Manager for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-dependency)
Name: [Test Runner for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test)
Name: [TestNg TestSuite Runner: ](https://marketplace.visualstudio.com/items?itemName=DebrajBhal.testng-testsuite-runner)


### Artigos de referência
[Como instalar o maven no Windows](https://dicasdeprogramacao.com.br/como-instalar-o-maven-no-windows/)

[Extensões para Visual Studio Code](https://spurqlabs.com/how-to-use-visual-studio-code-for-java-selenium-automation/)

[Aprende a ver o arquivo setting.json no vscode](https://balta.io/blog/visual-studio-code-instalacao-customizacao#:~:text=Para%20acessar%20as%20configurações%2C%20basta,o%20JSON%20das%20configurações%20atuais)

[Erro: Google Exception in thread "main" org.openqa.selenium.remote.http.ConnectionFailedException: Unable to establish websocket connection to http://localhost:56310](https://www.youtube.com/watch?v=eRQiSt0er4Y)

[Allure report com JUNIT 5](https://allurereport.org/docs/junit5/)

[Veja a minha conversa com o chatGPT para configurar este projeto](https://chat.openai.com/share/63b4581e-296c-4c38-bb1f-8619bcff800f)

[Veja a minha conversa com o chatGPT para gerar o report deste projeto](https://chatgpt.com/c/44038b2c-0b73-4f4b-aa57-343514f8b72c)