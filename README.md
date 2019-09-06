# JSF E Docker
Esta atividade consiste na criação de uma aplicação
com docker, JSF e fazer o uso de um datasource para 
as conexões com o banco de dados.

Para a criação das imagens e execução dos containers corretamente
é necessário executar os arquivos .sh criados para controlar os containers

### Para criar as imagens e executar

    sudo ./FirstTime.sh 

O comando deve ser executado a partir da raiz do projeto, assim como os demais.

### Para parar os containers e manter os dados salvos

    sudo ./Stop.sh
    
### Para iniciar os containers já existentes

    sudo ./Start.sh
    
### Para remover os containers e zerar os dados

    sudo ./Kill.sh

