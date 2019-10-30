use SneakersDB;

insert into `users`(`id`,`firstname`, `lastname`, `dateofbirth`, `phone`, `email`, `address`, `username`,`password`,`enabled`, moneybalance)
    values(1, "firstname", "lastname", "2020/02/20", "111-111-1111", "admin@Example.com", "0632 america st.", "admin", "$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny", true, 10000.00),
        (2, "firstname", "lastname", "2020/02/20", "222-222-2222", "buyer@Example.com", "987 downtown st.", "buyer","$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny",true, 500.00),
        (3, "firstname", "lastname", "2020/02/20", "333-333-3333", "seller@Example.com", "643 broadway ave.", "seller", "$2a$10$DlLWMpK5t5o/UxLj5tOJfO9UgtY78M23DYjUidZ71emys4r7kycny", true, 250.00);

insert into `roles`(`id`,`role`)
    values(1,"ROLE_ADMIN"), (2,"ROLE_BUYER"), (3,"ROLE_SELLER");
    
insert into `users_roles`(`user_id`,`role_id`)
    values(1,1),(1,2),(1,3),(2,2),(3,2),(3,3);
    
insert into brands (name) values 
('Nike'),
('Adidas'),
('New Balance'),
('Vans'),
('Air Jordan'),
('Converse')
;

insert into primarycolors (name) values 
('Black'),
('Blue'),
('White'),
('Gray'),
('Red'),
('Pink'),
('Orange'),
('Green'),
('Multi'),
('Gold'),
('Silver'),
('Brown'),
('Purple')
;

insert into secondarycolors (name) values 
('Black'),
('Blue'),
('White'),
('Gray'),
('Red'),
('Pink'),
('Orange'),
('Green'),
('Multi'),
('Gold'),
('Silver'),
('Brown'),
('Purple')
;

insert into sizes (shoesize) values
(1), (2), (3), (4), (5),
(6), (7), (8), (9), (10),
(11), (12), (13), (14), (15);

insert into models (name, brandid, releaseyear, primarycolorid, secondarycolorid, msrp_price) values
('Jordan 1', 5, 1991, 5, 1, 150.00),
('KD1', 1, 2012, 2, 3, 175.00),
('Yeezy V350', 2, 2015, 3, 4, 220.00),
('Back to the Future', 1, 2016, 4, 3, 720.00),
('Stan Smith', 2, 2012, 3, 2, 80.00),
('Old Skool', 4, 2000, 1, 3, 60.00),
('OMN1S', 3, 2019, 2, 6, 210.00),
('React Presto', 1, 2019, 1, 3, 120.00),
('Jordan 1 Retro Shattered Backboard 3.0', 5, 2019, 1, 7, 220.00),
('997S Bodega', 3, 2019, 4, 13, 400.00),
('NMD', 2, 2015, 1, 1,  125.00),
('Chuck Taylor All Star', 6, 2017, 5, 3, 70.00),
('Stan Smith', 2, 2015, 3, 8, 80.00),
('Air Max 90 OFF-WHITE Desert Ore', 1, 2018, 12, 7, 250.00),
('Air Huarache Sneaker', 1, 2018, 3, 6, 175.00),
('Jordan 11 Retro Concord', 5, 2018, 3, 1, 225.00),
('Jordan 4 Retro White Cement', 5, 2016, 3, 4, 250.00),
('Slip-on Classic Canvas', 4, 2016, 3, 1, 75.00),
('574 Classic Runner', 3, 2010, 3, 8, 100.00)
;

insert into types (name) values
('Infant'),
('Toddler'),
('Women'),
('Men');

insert into models_types (modelid, typeid) values
(1,4), (1,3), (2,4), (3,1), (3,2), (3,3), (3,4);

insert into status (name) values
('Active'), ('Inactive'), ('Sold');

insert into shoeconditions (name) values
('New'), ('Used');

insert into listings 
(statusid, modelid, shoeconditionid, shoesizeid, description, 
buynowprice, minstartingprice, listdate, enddate, sellerid, photo, typeid)
values
(1, 1, 1, 10, '<p>Original AJ1 in box</p>', 200.00, 150.00, '2019-10-01', '2019-10-30', 3, '/images/1.jpg', 4),
(3, 3, 2, 15, '<p>Turtledoves slightly used</p>', 220.00, 190.00,  '2019-09-15', '2019-10-2', 3, '/images/2.jpg', 3),
(1, 4, 1, 9, '<p>Marty McFly!</p>', 1000.00, 800.00, '2019-10-25', '2019-11-20', 3, '/images/3.jpg', 4),
(1, 2, 1, 12, '<p>The very 1st Kevin Durant shoe</p>', 200.00, 150.00, '2019-10-25', '2019-11-22', 3, '/images/4.jpg', 4),
(1, 5, 1, 11, '<p>New tennis shoes</p>', 80.00, 70.00, '2019-10-01', '2019-11-2', 3, '/images/5.jpg', 4),
(1, 6, 1, 6, '<p>Skate shoes</p>', 60.00, 50.00, '2019-10-15', '2019-11-15', 3, '/images/6.jpg', 3),
(1, 7, 1, 14, '<p>Kawhi Leonard Basketball shoes</p>', 210.00, 200.00, '2019-10-21', '2019-11-30', 3, '/images/7.jpg', 4),
(1, 8, 1, 8, '<p>Casual lifestyle sneakers</p>', 120.00, 90.00, '2019-10-24', '2019-11-18', 3, '/images/8.jpg', 3),
(1, 9, 1, 13, '<p>Just released Retro Shattered Backboard</p>', 300.00, 250.00, '2019-10-29', '2019-11-30', 3, '/images/9.jpg', 4),
(1, 10, 1, 8, '<p>997S Collab</p>', 500.00, 450.00, '2019-10-29', '2019-11-30', 3, '/images/10.jpg', 3),
(1, 11, 2, 7, '<p>All black NMDs</p>', 75.00, 50.00, '2019-10-29', '2019-11-30', 3, '/images/11.jpg', 3),
(1, 12, 2, 7, '<p>OG Chuck Taylors</p>', 75.00, 50.00, '2019-10-29', '2019-11-30', 3, '/images/12.jpg', 3),
(1, 13, 1, 6, '<p>Casual Stans</p>', 70.00, 65.00, '2019-10-29', '2019-11-30', 3, '/images/13.jpg', 3),
(1, 14, 1, 10, '<p>Off-White</p>', 700.00, 500.00, '2019-10-29', '2019-11-30', 3, '/images/14.jpg', 4),
(1, 15, 1, 7, '<p>Very clean</p>', 175.00, 150.00, '2019-10-29', '2019-11-30', 3, '/images/15.jpg', 3),
(1, 16, 1, 9, '<p>Very rare</p>', 400.00, 390.00, '2019-10-29', '2019-11-30', 3, '/images/16.jpg', 4),
(1, 17, 1, 10, '<p>Jordan 4s</p>', 300.00, 280.00, '2019-10-29', '2019-11-30', 3, '/images/17.jpg', 4),
(1, 18, 1, 8, '<p>Super Comfortable</p>', 75.00, 74.00, '2019-10-29', '2019-11-30', 3, '/images/18.jpg', 4),
(1, 6, 1, 9, '<p>Yacht Club</p>', 100.00, 90.00, '2019-10-29', '2019-11-30', 3, '/images/19.jpg', 4),
(1, 19, 1, 10, '<p>Casual runners</p>', 100.00, 90.00, '2019-10-29', '2019-11-30', 3, '/images/20.jpg', 4),
(1, 11, 1, 11, '<p>Hu Pharrell Solar Pack</p>', 400.00, 350.00, '2019-10-29', '2019-11-30', 3, '/images/21.jpg', 4)
;


insert into purchases (listingid, saleprice, sellerid, buyerid, datesold)
values
(2, 265.00, 3, 2, '2019-10-07');

insert into bids (listingid, bidprice, date, buyerid) values
(1, 205.00, '2019-10-02', 2),
(1, 210.00, '2019-10-25', 1),
(2, 220.00, '2019-10-01', 1);

