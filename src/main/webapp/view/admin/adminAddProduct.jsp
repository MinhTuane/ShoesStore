<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function validateForm() {
        	my_form = document.getElementById("my_form");
    		my_form.submit();
        }
    </script>
</head>
<body>
    <div class="container mt-3">
        <h2>Add Product</h2>
        <form name="productForm" class="needs-validation" method="post" action="admin-AddProduct" id="my_form" enctype="multipart/form-data" >
            <div class="form-group">
                <label for="name">Product Name<span class="rq"> * </span></label>
                <input type="text" class="form-control" id="name" name="name" required>
                <div class="invalid-feedback">Please enter a product name.</div>
            </div>
            <div class="form-group">
                <label for="cost">Cost<span class="rq"> * </span></label>
                <input type="number" step="0.01" class="form-control" id="cost" name="cost" required>
                <div class="invalid-feedback">Please enter the cost.</div>
            </div>
            <div class="form-group">
                <label for="date">Date<span class="rq"> * </span></label>
                <input type="date" class="form-control" id="date" name="date" required>
                <div class="invalid-feedback">Please enter the date.</div>
            </div>
            <div class="form-group">
                <label for="sold">Sold Quantity<span class="rq"> * </span></label>
                <input type="number" class="form-control" id="sold" name="sold" required>
                <div class="invalid-feedback">Please enter the sold quantity.</div>
            </div>
            <div class="form-group">
                <label for="rate">Rate<span class="rq"> * </span></label>
                <input type="number" step="0.01" class="form-control" id="rate" name="rate" required>
                <div class="invalid-feedback">Please enter the rate.</div>
            </div>
            <div class="form-group">
                <label for="image">Image<span class="rq"> * </span></label>
                <input type="file" class="form-control-file" id="image" name="image" required>
                <div class="invalid-feedback">Please upload an image.</div>
            </div>
            <button type="button" onclick="validateForm()" class="btn btn-primary">Add Product</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</body>
</html>
