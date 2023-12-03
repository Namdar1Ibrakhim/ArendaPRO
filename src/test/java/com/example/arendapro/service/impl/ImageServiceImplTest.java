//package com.example.arendapro.service.impl;
//
//import com.example.arendapro.exceptions.ImageUploadException;
//import com.example.arendapro.util.MinioProperties;
//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;
//import io.minio.errors.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith({MockitoExtension.class, SpringExtension.class})
//class ImageServiceImplTest {
//
//    @Mock
//    private MinioClient minioClient;
//
//    @Mock
//    private InputStream inputStream;
//
//    @Mock
//    private MinioProperties minioProperties;
//
//    @InjectMocks
//    private ImageServiceImpl imageService;
//
//    @Test
//    void testUpload() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        MockMultipartFile file = new MockMultipartFile(
//                "file", "test-image.jpg", "image/jpeg", "Spring Framework".getBytes());
//
//        when(minioProperties.getBucket()).thenReturn("test-bucket");
//
//        doNothing().when(minioClient).makeBucket(any());
//        when(minioClient.bucketExists(any())).thenReturn(false);
//
//        String fileName = imageService.upload(file);
//
//        assertNotNull(fileName);
//        assertTrue(fileName.endsWith(".jpg"));
//
//        verify(minioClient, times(1)).putObject(any());
//    }
//
//    @Test
//    void testUploadException() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        MockMultipartFile file = new MockMultipartFile(
//                "file", "test-image.jpg", "image/jpeg", "Spring Framework".getBytes());
//
//        when(minioProperties.getBucket()).thenReturn("test-bucket");
//
//        doThrow(MinioException.class).when(minioClient).makeBucket(any());
//        when(minioClient.bucketExists(any())).thenReturn(false);
//
//        assertThrows(ImageUploadException.class, () -> imageService.upload(file));
//    }
//
//    @Test
//    void testUploadEmptyFile() {
//        MockMultipartFile file = new MockMultipartFile(
//                "file", "", "image/jpeg", new byte[0]);
//
//        assertThrows(ImageUploadException.class, () -> imageService.upload(file));
//    }
//
//    @Test
//    void testUploadEmptyFileName() {
//        MockMultipartFile file = new MockMultipartFile(
//                "", "", "image/jpeg", "Spring Framework".getBytes());
//
//        assertThrows(ImageUploadException.class, () -> imageService.upload(file));
//    }
//}
//
