<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Us - Toy Store</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #fff0f5;
            padding: 40px;
            margin: 0;
        }

        h1 {
            color: #d63384;
            text-align: center;
            font-size: 36px;
            font-weight: bold;
        }

        .back-container {
            max-width: 1000px;
            margin: 0 auto 20px auto;
            text-align: left;
        }

        .back-btn {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #ff85a2;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.2s ease;
        }

        .back-btn:hover {
            background-color: #e65c8c;
        }

        .contact-container {
            max-width: 900px;
            margin: 0 auto;
            background: #ffe6f0;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(214, 51, 132, 0.1);
            color: #444;
        }

        .intro-text {
            font-size: 17px;
            line-height: 1.7;
            margin-bottom: 30px;
            font-weight: 500;
        }

        .contact-info {
            margin-bottom: 30px;
            font-size: 16px;
            line-height: 1.7;
        }

        .qr-section {
            text-align: center;
            margin: 40px 0;
        }

        .qr-section img {
            width: 200px;
            height: 200px;
            border-radius: 10px;
            border: 2px solid #ffc0cb;
        }

        .contact-form label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            color: #d63384;
        }

        .contact-form input, .contact-form textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ffc0cb;
            border-radius: 8px;
            font-size: 14px;
            background: #fff;
        }

        .contact-form textarea {
            resize: vertical;
            height: 120px;
        }

        .contact-form button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff85a2;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }

        .contact-form button:hover {
            background-color: #e65c8c;
        }
    </style>
</head>
<body>

    <div class="back-container">
        <a href="ViewProductListController" class="back-btn">← Back</a>
    </div>

    <h1>Contact Us</h1>

    <div class="contact-container">
        <div class="intro-text">
            We'd love to hear from you! If you have any questions, suggestions, or need help, feel free to reach out. You can also scan the QR code below to contact us directly through your favorite app.
        </div>

        <div class="qr-section">
            <img src="img/QR.jpg" alt="QR Code for Contact" />
            <p>Scan this QR to connect with us</p>
        </div>

        <div class="contact-info">
            <p><strong>Address:</strong> Can Tho, Ninh Kieu</p>
            <p><strong>Phone:</strong> (+84) 374-721-054</p>
            <p><strong>Email:</strong> support@toystore.com</p>
            <p><strong>Opening Hours:</strong> 8:00 AM - 6:00 PM (Mon - Sat)</p>
        </div>
    </div>

</body>
</html>
