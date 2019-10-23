use SneakersDB;

insert into `users`(`id`,`firstname`, `lastname`, `dateofbirth`, `phone`, `email`, `username`,`password`,`enabled`)
    values(1, "firstname", "lastname", "2020/02/20", "111-111-1111", "admin@Example.com", "admin", "$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny", true),
        (2, "firstname", "lastname", "2020/02/20", "222-222-2222", "buyer@Example.com", "buyer","$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny",true),
        (3, "firstname", "lastname", "2020/02/20", "333-333-3333", "seller@Example.com", "seller", "$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny", true);

insert into `roles`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_BUYER"), (3,"ROLE_SELLER");
    
insert into `users_roles`(`user_id`,`role_id`)
    values(1,1),(1,2),(1,3),(2,2),(3,2),(3,3);
    
insert into brands (name) values 
('nike'),
('adidas'),
('new balance'),
('vans'),
('air jordan');

insert into primarycolors (name) values 
('black'),
('blue'),
('white'),
('gray'),
('red'),
('pink');

insert into secondarycolors (name) values 
('black'),
('blue'),
('white'),
('gray'),
('red'),
('pink');

insert into sizes (shoesize) values
(1), (2), (3), (4), (5),
(6), (7), (8), (9), (10),
(11), (12), (13), (14), (15);

insert into models (name, brandid, releaseyear, primarycolorid, secondarycolorid, msrp_price) values
('jordan 1', 5, 1991, 5, 1, 150.00),
('KD1', 1, 2012, 2, 3, 175.00),
('yeezy', 2, 2015, 3, 4, 220.00);

insert into types (name) values
('infant'),
('toddler'),
('women'),
('men');

insert into models_types (modelid, typeid) values
(1,4), (1,3), (2,4), (3,1), (3,2), (3,3), (3,4);

insert into status (name) values
('active'), ('inactive'), ('sold');

insert into shoeconditions (name) values
('new'), ('used');

insert into listings 
(statusid, modelid, shoeconditionid, shoesizeid, description, 
buynowprice, minstartingprice, listdate, enddate, sellerid, photo, typeid)
values
(1, 1, 1, 10, 'original AJ1 in box', 200.00, 150.00, '2019-10-01', '2019-10-30', 3, 'xyz.png', 4),
(3, 3, 2, 15, 'turtledoves slightly used', 220.00, 190.00,  '2019-09-15', '2019-10-2', 3, 'abc.jpg', 3);

insert into purchases (listingid, saleprice, sellerid, buyerid, datesold)
values
(2, 265.00, 3, 2, '2019-10-07');

insert into bids (listingid, bidprice, date, buyerid) values
(1, 205.00, '2019-10-02', 2),
(1, 210.00, '2019-10-25', 1),
(2, 220.00, '2019-10-01', 1);

