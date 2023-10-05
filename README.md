# ChampionsAPI

API para salvar, editar, buscar campeoes

#API Mapping 
@GET
/champions
Retorna uma lista com todos os campeoes cadastrados
/champions/{id}
Retorna um campeao especificado pelo Id
/champions/{id}/images
Retorna uma lista de imagens do campeao especificado pelo Id
/champions/{id}/images/{tipo}
Retorna uma lista de imagens de um tipo especifico de um campeao

/images
Retorna uma lista das imagens cadastradas
/images/{id}
Retorna uma uma imagem especificada pelo id

@POST
/champions
Salva um novo campeao

/images
Salva uma nova imagem

@PUT
/champions/{id}
Edita o campeao especificado pelo id
/champions/{id}/images/{id}
Adiciona a imagem para o campeao 

/images/{id}
Edita uma imagem especificada pelo id

@DELETE
/champions/{id}
Deleta o campeao especificado pelo id

/images/{id}
Deleta uma imagem especificada pelo id

#Outros detalhes

O campo tipo da imagem pode ser SPLASH, MINIATURA ou LOADING
O campeao e a imagem podem ser criados a parte e depois interligados pelo PUT, sendo especificado o Id do campeao e da imagem
