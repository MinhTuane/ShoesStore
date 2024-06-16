<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Custom styles -->
    <style>
        body {
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Roboto', sans-serif;
        }

        .card {
            max-width: 400px;
            border: none;
            border-radius: 10px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #007bff;
            color: white;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            text-align: center;
            padding: 1.5rem;
        }

        .card-body {
            padding: 2rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-control {
            border-radius: 5px;
            padding: 1rem;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 0.75rem;
            font-size: 1rem;
            width: 100%;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .register-link {
            text-align: center;
            margin-top: 1rem;
        }

        .error-message {
            color: #dc3545;
            font-size: 0.9rem;
            text-align: center;
            margin-top: 1rem;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3>Login</h3>
                </div>
                <div class="card-body">
                    <%-- Error Message Section --%>
                    <div id="error-message" class="error-message">
                        <% if (request.getAttribute("ErrorLogin") != null) { %>
                            <%= request.getAttribute("ErrorLogin") %>
                        <% } %>
                    </div>

                    <form id="loginForm" action="login" method="POST">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>

                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="rememberMe">
                            <label class="form-check-label" for="rememberMe">Remember me</label>
                        </div>

                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>

                    <div class="register-link">
                        <a href="register">Create an account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Custom JavaScript for error message popup -->
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Check if URL contains error parameter
        var urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('error')) {
            String error =(String) request.getAttribute("ErrorLogin");
            showAlert(error);
        }
    });

    function showAlert(message) {
        alert(message);
    }
</script>

</body>
</html>
