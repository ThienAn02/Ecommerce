
Ecommerce Website with Admin and User Roles
This project is a fully-functional ecommerce website with separate sections for admin and user roles. It uses Thymeleaf for front-end rendering and Spring Boot on the backend, with features such as role-based authentication, product management, and cart functionality with payment integration.

##Features
1. Admin Section
- Category Management: Admins can add, edit, and delete product categories.
- Product Management: Admins can manage the entire product catalog, including adding new products, updating existing ones (description, images, prices), and deleting products.
2. User Section
- Role-based Authentication:
- Users are authenticated using Spring Security with support for both Google OAuth and custom email/password login.
- Admins have special access to manage categories and products.
- Products Page: Users can browse all available products, view details, and add items to their shopping cart.
- Cart Management: Users can add, update, or remove products from their cart.
3. Responsive Design
- Built with Thymeleaf templates, the website is fully responsive and works on mobile, tablet, and desktop devices.
##Tech Stack
- Front-End: Thymeleaf, CSS, JavaScript
- Back-End: Spring Boot, Spring Security, Spring Data JPA
- Authentication: Google OAuth, Custom Authentication (Spring Security)
