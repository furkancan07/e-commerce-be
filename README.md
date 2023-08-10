
# E ticaret Sitesi

Daha Bitmedi...



## Özellikler

- Yapıldı: Admin için kayıt olma,giriş işlemleri
- Yapıldı: Admin için ürün ekleme ve ürün özelliklerini  girme
- Yapıldı : User için giriş,kayı,şifremi unuttum kısmı
- Yapıldı : Ürüne yorum ekleme,değerlendirme
- Yapıldı : Ürünü sepete ekleme,kaldırma işlemleri
- Yapıldı : Ürünler kategorilere göre listelendi
- Yapıldı : Mağaza görüntüleme ve mağazanın başka ürünlerinin listesi oluşturuldu
- Planlanan: Ödeme işlemleri
- Planlanan: Geçmiş Siparişleri görüntüleme
- Planlanan: Mağaza sahibi ile mesajlaşma

  
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













  


  
