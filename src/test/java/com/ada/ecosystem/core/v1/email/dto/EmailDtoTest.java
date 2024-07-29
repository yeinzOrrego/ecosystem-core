package com.ada.ecosystem.core.v1.email.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class EmailDtoTest {

    @Test
    public void testDefaultConstructor() {
        // Act
        EmailDto dto = new EmailDto();

        // Assert
        assertThat(dto.getAttachmentBase64()).isNotNull().isEmpty();
        assertThat(dto.getToEmail()).isNotNull().isEmpty();
        assertThat(dto.getCcEmail()).isNotNull().isEmpty();
        assertThat(dto.getBccEmail()).isNotNull().isEmpty();
        assertThat(dto.getUniqueEmail()).isNotNull().isEmpty();
        assertThat(dto.getFrom()).isNull();
        assertThat(dto.getSubject()).isNull();
        assertThat(dto.getContent()).isNull();
        assertThat(dto.getHtml()).isNull();
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        EmailDto dto = new EmailDto();
        AddressConfigDto addressConfig = new AddressConfigDto();
        addressConfig.setAddress("test@example.com");
        addressConfig.setPersona("Test Person");

        AttachmentBase64ConfigDto attachment = new AttachmentBase64ConfigDto();
        attachment.setFileName("test.txt");
        attachment.setFileContentStringBase64("dGVzdCBjb250ZW50");
        attachment.setTypeContent("text/plain");

        dto.setFrom(addressConfig);
        dto.setSubject("Test Subject");
        dto.setContent("Test Content");
        dto.setHtml(true);
        dto.setAttachmentBase64(Collections.singletonList(attachment));
        dto.setToEmail(Collections.singletonList(addressConfig));
        dto.setCcEmail(Collections.singletonList(addressConfig));
        dto.setBccEmail(Collections.singletonList(addressConfig));
        dto.setUniqueEmail(Collections.singletonList("unique@example.com"));

        // Act & Assert
        assertThat(dto.getFrom()).isEqualTo(addressConfig);
        assertThat(dto.getSubject()).isEqualTo("Test Subject");
        assertThat(dto.getContent()).isEqualTo("Test Content");
        assertThat(dto.getHtml()).isTrue();
        assertThat(dto.getAttachmentBase64()).containsExactly(attachment);
        assertThat(dto.getToEmail()).containsExactly(addressConfig);
        assertThat(dto.getCcEmail()).containsExactly(addressConfig);
        assertThat(dto.getBccEmail()).containsExactly(addressConfig);
        assertThat(dto.getUniqueEmail()).containsExactly("unique@example.com");
    }

    @Test
    public void testToString() {
        // Arrange
        EmailDto dto = new EmailDto();
        dto.setSubject("Test Subject");
        dto.setContent("Test Content");

        // Act
        String toString = dto.toString();

        // Assert
        assertThat(toString).contains("subject=Test Subject");
        assertThat(toString).contains("content=Test Content");
        assertThat(toString).contains("attachmentBase64=[]");
        assertThat(toString).contains("toEmail=[]");
        assertThat(toString).contains("ccEmail=[]");
        assertThat(toString).contains("bccEmail=[]");
        assertThat(toString).contains("uniqueEmail=[]");
        assertThat(toString).contains("from=null");
        assertThat(toString).contains("html=null");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        EmailDto dto1 = new EmailDto();
        dto1.setSubject("Subject");
        dto1.setContent("Content");

        EmailDto dto2 = new EmailDto();
        dto2.setSubject("Subject");
        dto2.setContent("Content");

        EmailDto dto3 = new EmailDto();
        dto3.setSubject("Different Subject");
        dto3.setContent("Different Content");

        // Act & Assert
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    public void testAddToEmail() {
        // Arrange
        EmailDto dto = new EmailDto();
        AddressConfigDto addressConfig = new AddressConfigDto();
        addressConfig.setAddress("test@example.com");
        addressConfig.setPersona("Test Person");

        // Act
        dto.getToEmail().add(addressConfig);

        // Assert
        assertThat(dto.getToEmail()).containsExactly(addressConfig);
    }

    @Test
    public void testAddCcEmail() {
        // Arrange
        EmailDto dto = new EmailDto();
        AddressConfigDto addressConfig = new AddressConfigDto();
        addressConfig.setAddress("cc@example.com");
        addressConfig.setPersona("CC Person");

        // Act
        dto.getCcEmail().add(addressConfig);

        // Assert
        assertThat(dto.getCcEmail()).containsExactly(addressConfig);
    }

    @Test
    public void testAddBccEmail() {
        // Arrange
        EmailDto dto = new EmailDto();
        AddressConfigDto addressConfig = new AddressConfigDto();
        addressConfig.setAddress("bcc@example.com");
        addressConfig.setPersona("BCC Person");

        // Act
        dto.getBccEmail().add(addressConfig);

        // Assert
        assertThat(dto.getBccEmail()).containsExactly(addressConfig);
    }

    @Test
    public void testAddUniqueEmail() {
        // Arrange
        EmailDto dto = new EmailDto();

        // Act
        dto.getUniqueEmail().add("unique@example.com");

        // Assert
        assertThat(dto.getUniqueEmail()).containsExactly("unique@example.com");
    }

    @Test
    public void testAllFieldsSet() {
        // Arrange
        EmailDto dto = new EmailDto();
        AddressConfigDto from = new AddressConfigDto();
        from.setAddress("from@example.com");
        from.setPersona("From Person");

        dto.setFrom(from);
        dto.setSubject("Subject");
        dto.setContent("Content");
        dto.setHtml(true);
        dto.setAttachmentBase64(Collections.singletonList(new AttachmentBase64ConfigDto()));
        dto.setToEmail(Collections.singletonList(new AddressConfigDto()));
        dto.setCcEmail(Collections.singletonList(new AddressConfigDto()));
        dto.setBccEmail(Collections.singletonList(new AddressConfigDto()));
        dto.setUniqueEmail(Collections.singletonList("unique@example.com"));

        // Act & Assert
        assertThat(dto.getFrom()).isEqualTo(from);
        assertThat(dto.getSubject()).isEqualTo("Subject");
        assertThat(dto.getContent()).isEqualTo("Content");
        assertThat(dto.getHtml()).isTrue();
        assertThat(dto.getAttachmentBase64()).isNotEmpty();
        assertThat(dto.getToEmail()).isNotEmpty();
        assertThat(dto.getCcEmail()).isNotEmpty();
        assertThat(dto.getBccEmail()).isNotEmpty();
        assertThat(dto.getUniqueEmail()).isNotEmpty();
    }
}
