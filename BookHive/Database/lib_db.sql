-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2025 at 06:36 PM
-- Server version: 8.0.41
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lib_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `user_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `date_joined` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_id`, `name`, `username`, `password`, `status`, `date_joined`) VALUES
(1, 'Sahil Gupta', 'sahil', '1234', 'logged out', '2025-11-01'),
(2, 'Niraj Agrahari', 'niraj', '1234', 'logged out', '2025-11-02'),
(7, 'Manu Maurya', 'manu', '1234', 'logged out', '2025-11-11'),
(8, 'Tarushi Pandey', 'tarushi', '1234', 'logged out', '2025-11-11'),
(9, 'Shruti Jadhav', 'shruti', '1234', 'logged out', '2025-11-11');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `copies` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `publisher`, `genre`, `copies`, `status`) VALUES
(1, 'Computer Networks', 'Andrew S. Tanenbaum', 'Pearson', 'Computer Science', 25, 'available'),
(2, 'Object-Oriented Programming with C++', 'E. Balagurusamy', 'McGraw-Hill', 'Computer Science', 35, 'available'),
(3, 'Software Engineering', 'Ian Sommerville', 'Pearson', 'Computer Science', 25, 'available'),
(4, 'Programming in ANSI C', 'E. Balagurusamy', 'McGraw-Hill', 'Computer Science', 10, 'available'),
(5, 'Operating System Concepts', 'Abraham Silberschatz', 'Wiley', 'Computer Science', 50, 'available'),
(6, 'Data Structures and Algorithms Made Easy', 'Narasimha Karumanchi', 'CareerMonk', 'Computer Science', 20, 'available'),
(7, 'Database System Concepts', 'Silberschatz et al.', 'McGraw-Hill', 'Computer Science', 30, 'available'),
(8, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 'Fiction', 15, 'available'),
(9, 'To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 'Fiction', 20, 'available'),
(10, 'new book', 'new author', 'new publisher', 'new genre', 0, 'archived'),
(11, 'Exploring C', 'Yashwant Kanet', 'Wiley', 'Computer Science', 30, 'available'),
(12, 'The Art of Doing Science and Engineering', 'RIchard Hamming', 'Stripe Matter Inc', 'Mathematics and Science', 10, 'available'),
(13, 'A Brief History of Time', 'Stephen Hawking', 'Bantam', 'Science', 18, 'available'),
(14, 'The Art of War', 'Sun Tzu', 'Oxford University Press', 'Philosophy', 12, 'available'),
(15, 'The Lean Startup', 'Eric Ries', 'Crown Publishing', 'Business', 25, 'available'),
(16, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Harper', 'History', 14, 'available'),
(17, 'Atomic Habits', 'James Clear', 'Penguin Random House', 'Self-Help', 30, 'available'),
(18, 'The Psychology of Money', 'Morgan Housel', 'Harper Business', 'Finance', 22, 'available'),
(19, 'The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 'Fantasy', 18, 'available'),
(20, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', 'Bloomsbury', 'Fantasy', 40, 'available'),
(21, 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 'Fiction', 15, 'available'),
(22, 'Pride and Prejudice', 'Jane Austen', 'T. Egerton', 'Romance', 12, 'available'),
(23, 'Becoming', 'Michelle Obama', 'Crown Publishing', 'Biography', 10, 'available'),
(24, 'Rich Dad Poor Dad', 'Robert Kiyosaki', 'Plata Publishing', 'Finance', 35, 'available'),
(25, 'Think Like a Monk', 'Jay Shetty', 'Simon & Schuster', 'Self-Help', 28, 'available'),
(26, 'The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'HarperOne', 'Self-Help', 25, 'available'),
(27, 'Wings of Fire', 'A.P.J. Abdul Kalam', 'Universities Press', 'Biography', 20, 'available'),
(28, 'The Alchemist', 'Paulo Coelho', 'HarperTorch', 'Fiction', 32, 'available'),
(29, 'Deep Work', 'Cal Newport', 'Grand Central Publishing', 'Self-Help', 18, 'available'),
(30, 'The Intelligent Investor', 'Benjamin Graham', 'Harper Business', 'Finance', 22, 'available'),
(31, '1984', 'George Orwell', 'Secker & Warburg', 'Fiction', 16, 'available'),
(32, 'A Promised Land', 'Barack Obama', 'Crown Publishing', 'Biography', 10, 'available'),
(33, 'The Theory of Everything', 'Stephen Hawking', 'Jaico Publishing House', 'Science', 14, 'available'),
(34, 'Zero to One', 'Peter Thiel', 'Crown Business', 'Business', 25, 'available'),
(35, 'Introduction to Algorithms', 'Thomas H. Cormen', 'MIT Press', 'Computer Science', 40, 'available'),
(36, 'Clean Code', 'Robert C. Martin', 'Prentice Hall', 'Computer Science', 30, 'available'),
(37, 'The Pragmatic Programmer', 'Andrew Hunt', 'Addison-Wesley', 'Computer Science', 20, 'available'),
(38, 'The Selfish Gene', 'Richard Dawkins', 'Oxford University Press', 'Science', 18, 'available'),
(39, 'Educated', 'Tara Westover', 'Random House', 'Biography', 15, 'available'),
(40, 'The Power of Now', 'Eckhart Tolle', 'New World Library', 'Spirituality', 25, 'available');

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE `issue` (
  `issue_id` int NOT NULL,
  `book_id` int NOT NULL,
  `student_id` int NOT NULL,
  `issue_date` date NOT NULL,
  `return_date` date NOT NULL,
  `actual_return_date` date DEFAULT NULL,
  `return_status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`issue_id`, `book_id`, `student_id`, `issue_date`, `return_date`, `actual_return_date`, `return_status`) VALUES
(1, 1, 1, '2025-10-23', '2025-10-30', '2025-10-31', 'returned'),
(2, 1, 24, '2025-10-15', '2025-10-22', '2025-10-30', 'returned'),
(3, 1, 24, '2025-11-02', '2025-11-09', '2025-11-07', 'returned'),
(4, 10, 1, '2025-10-29', '2025-11-05', '2025-11-03', 'returned'),
(5, 10, 24, '2025-10-31', '2025-11-07', '2025-11-05', 'returned'),
(6, 10, 1, '2025-10-23', '2025-10-30', '2025-11-02', 'returned'),
(7, 2, 24, '2025-11-11', '2025-11-18', '2025-11-13', 'returned');

-- --------------------------------------------------------

--
-- Table structure for table `overdue`
--

CREATE TABLE `overdue` (
  `overdue_id` int NOT NULL,
  `issue_id` int NOT NULL,
  `book_id` int NOT NULL,
  `student_id` int NOT NULL,
  `days_late` int NOT NULL,
  `fine_amount` int NOT NULL,
  `fine_status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `overdue`
--

INSERT INTO `overdue` (`overdue_id`, `issue_id`, `book_id`, `student_id`, `days_late`, `fine_amount`, `fine_status`) VALUES
(13, 2, 1, 24, 8, 40, 'paid'),
(14, 6, 10, 1, 3, 15, 'paid');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `dept` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `name`, `contact`, `dept`) VALUES
(1, 'Niraj Agrahari', '9182736450', 'MCA-A'),
(2, 'Sahil Alam', '9592739450', 'MCA-A'),
(3, 'Rohan Bagoria', '9889220678', 'MCA-A'),
(7, 'Hindavi Chaudhari', '9816934356', 'MCA-A'),
(9, 'Mahesh Chavan', '4986902345', 'MCA-A'),
(12, 'Shivam Divakar', '9182739450', 'MCA-A'),
(14, 'Saurabh Eklare', '9889238678', 'MCA-A'),
(18, 'Pallavi Gond', '9273365178', 'MCA-A'),
(24, 'Sahil Gupta', '9769238678', 'MCA-A'),
(25, 'Shomesh Gupta', '9893574356', 'MCA-A'),
(26, 'Shweta Gupta', '9182731950', 'MCA-A'),
(28, 'Vishu Gupta', '4986702345', 'MCA-A'),
(29, 'Shruti Jadhav', '9893435856', 'MCA-A'),
(44, 'Manu Maurya', '9342345678', 'MCA-A'),
(57, 'Tarushi Pandey', '9086702345', 'MCA-A'),
(59, 'Nikhil Patel', '9342365178', 'MCA-A'),
(64, 'PUNDIR SANJANA SUMERSINGH', '9876543210', 'MCA-B'),
(65, 'QAZI FAHAD AHMED SHAHID HUSSAIN', '9823456789', 'MCA-B'),
(66, 'RAI VIVEK KRISHNA', '9934567890', 'MCA-B'),
(67, 'RAJBHAR PRITI', '9765432109', 'MCA-B'),
(68, 'RAY ASHISH KUMAR', '9890123456', 'MCA-B'),
(69, 'SAINI ABHISHEK ARUNKUMAR', '9012345678', 'MCA-B'),
(70, 'SAROJ AKASH CHANDRABHAN', '9123456789', 'MCA-B'),
(71, 'SASANE TEJAS DIGAMBAR', '9345678901', 'MCA-B'),
(72, 'SAWANT AADITYA SANDEEP', '9456789012', 'MCA-B'),
(73, 'SAWANT TEJAS PRASHANT', '9567890123', 'MCA-B'),
(74, 'SAYYED MOHAMMED SAIF GULAM HAIDER', '9678901234', 'MCA-B'),
(75, 'SHAH AMISHA NANDLAL', '9789012345', 'MCA-B'),
(76, 'SHAH ARPIT HIRENKUMAR', '9890123457', 'MCA-B'),
(77, 'SHEIKH MOHAMMED AFNAN SHAKIL', '9901234567', 'MCA-B'),
(78, 'SHIVHARE SHIVAM NANDKISHOR', '9012345679', 'MCA-B'),
(79, 'SHUKLA SHREAYA SHANTIBHUSHAN', '9123456790', 'MCA-B'),
(80, 'SHUKLA SURAJ JITENDRA', '9234567901', 'MCA-B'),
(81, 'SILVEIRA VINCENT MICHAEL', '9345679012', 'MCA-B'),
(82, 'SINGH ABHISHEK RAMASHREE', '9456790123', 'MCA-B'),
(83, 'SINGH AMAN OMPRAKASH', '9567901234', 'MCA-B'),
(84, 'SINGH GAURAV JITENDRA', '9679012345', 'MCA-B');

-- --------------------------------------------------------

--
-- Table structure for table `user_log`
--

CREATE TABLE `user_log` (
  `log_id` int NOT NULL,
  `user_id` int NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_log`
--

INSERT INTO `user_log` (`log_id`, `user_id`, `login_time`, `logout_time`) VALUES
(1, 1, '2025-11-11 18:50:50', '2025-11-11 23:01:30'),
(2, 1, '2025-11-11 20:11:51', '2025-11-11 23:01:30'),
(3, 2, '2025-11-11 20:16:54', '2025-11-11 21:13:12'),
(4, 2, '2025-11-11 20:17:32', '2025-11-11 21:13:12'),
(5, 1, '2025-11-11 20:38:51', '2025-11-11 23:01:30'),
(6, 1, '2025-11-11 20:40:45', '2025-11-11 23:01:30'),
(7, 1, '2025-11-11 20:49:47', '2025-11-11 23:01:30'),
(8, 2, '2025-11-11 20:50:14', '2025-11-11 21:13:12'),
(9, 1, '2025-11-11 21:12:06', '2025-11-11 23:01:30'),
(10, 1, '2025-11-11 21:12:46', '2025-11-11 23:01:30'),
(11, 2, '2025-11-11 21:13:08', '2025-11-11 21:13:12'),
(12, 1, '2025-11-11 22:20:42', '2025-11-11 23:01:30'),
(13, 1, '2025-11-11 23:01:23', '2025-11-11 23:01:30'),
(14, 7, '2025-11-11 23:01:34', '2025-11-11 23:01:41');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_id_UNIQUE` (`user_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `book_id_UNIQUE` (`book_id`);

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`issue_id`),
  ADD UNIQUE KEY `issue_id_UNIQUE` (`issue_id`),
  ADD KEY `student_id_idx` (`student_id`),
  ADD KEY `book_id_idx` (`book_id`);

--
-- Indexes for table `overdue`
--
ALTER TABLE `overdue`
  ADD PRIMARY KEY (`overdue_id`),
  ADD UNIQUE KEY `overdue_id_UNIQUE` (`overdue_id`),
  ADD KEY `issue_id_idx` (`issue_id`),
  ADD KEY `student_id_idx` (`student_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `student_id_UNIQUE` (`student_id`);

--
-- Indexes for table `user_log`
--
ALTER TABLE `user_log`
  ADD PRIMARY KEY (`log_id`),
  ADD UNIQUE KEY `log_id_UNIQUE` (`log_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
  MODIFY `issue_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `overdue`
--
ALTER TABLE `overdue`
  MODIFY `overdue_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `user_log`
--
ALTER TABLE `user_log`
  MODIFY `log_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issue`
--
ALTER TABLE `issue`
  ADD CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  ADD CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);

--
-- Constraints for table `overdue`
--
ALTER TABLE `overdue`
  ADD CONSTRAINT `issue_id` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`issue_id`),
  ADD CONSTRAINT `studentid` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
