# ChampionsAPI

Essa API foi projetada para gerenciar informações sobre campeões. A API fornece funcionalidades para criar, atualizar, excluir e listar campeões.

### Como usar

Para fazer a API funcionar, deve-se baixar o arquivo [zip](https://drive.google.com/file/d/1zMN_JyimABBy7TQL9ZYet19dphbW1uds/view?usp=sharing) e descompacta-lo, abrindo o cmd no mesmo diretorio que esta os arquivos basta digitar

```
docker-compose up
```
A aplicação ira iniciar na porta 8080 junto com um servidor mysql que estara na porta 3306

## Database Diagram
<p align="center">
  <img src="./images/DatabaseDiagram.png" width=75% height=75%>
</p>

## API Mapping 

| Mapping | Path | Função |
| ------------- | ------------- | ------------- |
| GET | /champions | Retorna uma lista com todos os campeoes cadastrados |
| GET | /champions/{id} | Retorna um campeao especificado pelo Id |
| GET | /champions/{id}/images | Retorna uma lista de imagens do campeao especificado pelo Id |
| GET | /champions/{id}/images/{tipo} | Retorna uma lista de imagens de um tipo especifico de um campeao |
| GET | /images | Retorna uma lista das imagens cadastradas |
| GET | /images/{id} | Retorna uma uma imagem especificada pelo id |
| POST | /champions | Salva um novo campeao |
| POST | /images | Salva uma nova imagem |
| POST | /images/{tipo} | Salva uma nova imagem com um tipo especificado|
| PUT | /champions/editar/tudo/{id} | Edita tudo do campeao especificado pelo id |
| PUT | /champions/editar/parcial/{id} | Edita parcialmente o campeao especificado pelo id |
| PUT | /champions/{id}/images/{id} | Adiciona a imagem para o campeao |
| PUT | /images/editar/tudo/{id} | Edita tudo da imagem especificada pelo id |
| PUT | /images/editar/parcial/{id} | Edita parcialmente a imagem especificada pelo id |
| DELETE | /champions/{id} | Deleta o campeao especificado pelo id |
| DELETE | /images/{id} | Deleta uma imagem especificada pelo id |

## Outros detalhes

O campo tipo da imagem pode ser **SPLASH**, **MINIATURA** ou **LOADING**;
```Json
{
    "tipo":"SPLASH"
}
```

O campeao e a imagem podem ser criados a parte e depois interligados pelo PUT, sendo especificado o Id do campeao e da imagem;
```
/champions/1/images/2        # Interligando campeao id 1 com imagem id 2
```
