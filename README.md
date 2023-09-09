
# E ticaret Sitesi

Backend tarafında spring boot frontend tarafında react kullanılarak yapılmış basit bir e ticaret uygulaması



## Özellikler

- Yapıldı: Admin için kayıt olma,giriş işlemleri
- Yapıldı: Admin için ürün ekleme ve ürün özelliklerini  girme
- Yapıldı : User için giriş,kayıt,şifremi unuttum kısmı
- Yapıldı : Ürüne yorum ekleme,değerlendirme
- Yapıldı : Ürünü sepete ekleme,kaldırma işlemleri
- Yapıldı : Ürünler kategorilere göre listelendi
- Yapıldı : Mağaza görüntüleme ve mağazanın başka ürünlerinin listesi oluşturuldu
- Yapıldı: Ödeme işlemleri




  
## API Kullanımı

#### Admin kayıt olma-Mağaza açma

```http
  Post /api/createAdmin
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `Body` | `Map` | **Gerekli**. API anahtarınız. |

#### Admin giriş yapma
```http
  Post /api/adminLogin
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `authorization` | `String` | **Gerekli**. API anahtarınız. |

#### Ürün ekleme
```http
  Post /api/createProduct/{$username}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Ürün Silme
```http
  Delete /api/deleteProduct/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Ürün Güncelleme
```http
  Put /api/updateProduct/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |


#### Bir Mağazaya Ait Tüm Ürünleri Getirme
```http
  Get /api/getProducts/{$username}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |

#### Kategoriye göre ürün getimre
```http
  Get /api/getCategoryProducts/{$category}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `category` | `String` | **Gerekli**. API anahtarınız. |

#### Aranan Ürünü Getirme
```http
  Get /api/getSearchProducts/{$value}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `value` | `String` | **Gerekli**. API anahtarınız. |

#### idye göre ürün getimre
```http
  Get /api/getProduct/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |


#### Like atma
```http
  Get /api/plusLike/{$id}/{$email}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
| `email` | `String` | **Gerekli**. API anahtarınız. |

#### Like Geri Alma
```http
  Get /api/minusLike/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |



#### Kullanıcınının Beğemdiği ürünleri Listeleme
```http
  Get /api/getLikeList/{$email}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Gerekli**. API anahtarınız. |





#### User kayıt olma

```http
  Post /api/createUser
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `Body` | `Map` | **Gerekli**. API anahtarınız. |

#### User Giriş Yapma
```http
  Post /api/userLogin
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `authorization` | `String` | **Gerekli**. API anahtarınız. |

#### User Şifremi Unuttum
```http
  Post /api/forgot
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `Body` | `Map` | **Gerekli**. API anahtarınız. |

#### User Bilgilerini Getirme
```http
  Post /api/getUser/{$id]
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Sepete Ekleme
```http
  Post /api/addCart/${email}/${productId}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Gerekli**. API anahtarınız. |
| `productId` | `Long` | **Gerekli**. API anahtarınız. |

#### Sepetten Kaldırma
```http
  Delete /api/deleteCart/${cartId}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `cartId` | `Long` | **Gerekli**. API anahtarınız. |

#### Sepettekilerin Listesini Getime
```http
  Get /api/getCarts/${email}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Gerekli**. API anahtarınız. |

#### Ürüne Yorum Ekleme
```http
  Post /api/addComent/${email}/${productId}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Gerekli**. API anahtarınız. |
| `productId` | `Long` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Yorum Silme
```http
  Delete /api/deleteComment/${commentId}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `commentId` | `Long` | **Gerekli**. API anahtarınız. |

#### Bir Ürüne Ait Yorumları Getirme
```http
  Get /api/getComments/${productId}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `productId` | `Long` | **Gerekli**. API anahtarınız. |

#### Kategori oluşturma
```http
  Get /api/createCategory/
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Kategori Silme
```http
  Get /api/deleteCategory/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Kategorileri Getirme
```http
  Get /api/getCategories
  
```

#### Sipariş Alma
```http
  Get /api/createOrder/{$id}/{$email}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
| `email` | `String` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Sipariş İptal Etme
```http
  Get /api/deleteOrder/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Bir mağazaya ait sipariş listesini getirme
```http
  Get /api/getOrderList/{$username}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |


#### Bir Kullanıcının Yaptığı Siparişleri getirme
```http
  Get /api/getMyOrderList/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Gerekli**. API anahtarınız. |


#### Sipariş durumunu güncelleme
```http
  Get /api/updateOrderStatus/{$id}
  
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |


## Sitenin Linki
[Siteyi](https://rf-ecommerce.vercel.app) canlı görmek için tıkla.

## Video
[Video](https://www.youtube.com/watch?v=Oip3QkVyclE&list=PLoFXp7qbir3fKTMj84uEk7S9nk1A3g6NK)

  
## İletişim

İletişim için canfurkan903@gmail.com adresine e-posta gönderin 

  
## Kullanılan Teknolojiler

**Frontend:** React, Redux, Vite, MaterialUI,Axios,Router

**Backend:** Spring Boot

**DataBase** MySql

**Yayınlama** Vercel,Railway














  


  
