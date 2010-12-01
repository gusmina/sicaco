/**
 * 
 */
package com.mad.utilidades;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;

/**
 * Number2Letter tiene como objetivo principal el ofrecer
 * una interface la cual sea simple y sencilla, para la conversion
 * de cadenas de numero a letras.
 * 
 * @author Mauricio
 * @version 1.0
 */
public class Number2Letter {
	
	private HashMap cardinal;
	
	public Number2Letter(){
		cardinal = new HashMap();
		
		//inicializamos los valores
		cardinal.put(new Integer(1),"UN");
		cardinal.put(new Integer(2),"DOS");
		cardinal.put(new Integer(3),"TRES");
		cardinal.put(new Integer(4),"CUATRO");
		cardinal.put(new Integer(5),"CINCO");
		cardinal.put(new Integer(6),"SEIS");
		cardinal.put(new Integer(7),"SIETE");
		cardinal.put(new Integer(8),"OCHO");
		cardinal.put(new Integer(9),"NUEVE");
		cardinal.put(new Integer(10),"DIEZ");
		cardinal.put(new Integer(11),"ONCE");
		cardinal.put(new Integer(12),"DOCE");
		cardinal.put(new Integer(13),"TRECE");
		cardinal.put(new Integer(14),"CATORCE");
		cardinal.put(new Integer(15),"QUINCE");
		cardinal.put(new Integer(16),"DIECISEIS");
		cardinal.put(new Integer(17),"DIECISIETE");
		cardinal.put(new Integer(18),"DIECIOCHO");
		cardinal.put(new Integer(19),"DIECINUEVE");
		cardinal.put(new Integer(20),"VEINTE");
		cardinal.put(new Integer(21),"VEINTIUN");
		cardinal.put(new Integer(22),"VEINTIDOS");
		cardinal.put(new Integer(23),"VEINTITRES");
		cardinal.put(new Integer(24),"VEINTICUATRO");
		cardinal.put(new Integer(25),"VEINTICINCO");
		cardinal.put(new Integer(26),"VEINTISEIS");
		cardinal.put(new Integer(27),"VEINTISIETE");
		cardinal.put(new Integer(28),"VEINTIOCHO");
		cardinal.put(new Integer(29),"VEINTINUEVE");
		cardinal.put(new Integer(30),"TREINTA");
		cardinal.put(new Integer(31),"TREINTA Y UN");
		cardinal.put(new Integer(32),"TREINTA Y DOS");
		cardinal.put(new Integer(33),"TREINTA Y TRES");
		cardinal.put(new Integer(34),"TREINTA Y CUATRO");
		cardinal.put(new Integer(35),"TREINTA Y CINCO");
		cardinal.put(new Integer(36),"TREINTA Y SEIS");
		cardinal.put(new Integer(37),"TREINTA Y SIETE");
		cardinal.put(new Integer(38),"TREINTA Y OCHO");
		cardinal.put(new Integer(39),"TREINTA Y NUEVE");
		cardinal.put(new Integer(40),"CUARENTA");
		cardinal.put(new Integer(41),"CUARENTA Y UN");
		cardinal.put(new Integer(42),"CUARENTA Y DOS");
		cardinal.put(new Integer(43),"CUARENTA Y TRES");
		cardinal.put(new Integer(44),"CUARENTA Y CUATRO");
		cardinal.put(new Integer(45),"CUARENTA Y CINCO");
		cardinal.put(new Integer(46),"CUARENTA Y SEIS");
		cardinal.put(new Integer(47),"CUARENTA Y SIETE");
		cardinal.put(new Integer(48),"CUARENTA Y OCHO");
		cardinal.put(new Integer(49),"CUARENTA Y NUEVE");
		cardinal.put(new Integer(50),"CINCUENTA");
		cardinal.put(new Integer(51),"CINCUENTA Y UN");
		cardinal.put(new Integer(52),"CINCUENTA Y DOS");
		cardinal.put(new Integer(53),"CINCUENTA Y TRES");
		cardinal.put(new Integer(54),"CINCUENTA Y CUATRO");
		cardinal.put(new Integer(55),"CINCUENTA Y CINCO");
		cardinal.put(new Integer(56),"CINCUENTA Y SEIS");
		cardinal.put(new Integer(57),"CINCUENTA Y SIETE");
		cardinal.put(new Integer(58),"CINCUENTA Y OCHO");
		cardinal.put(new Integer(59),"CINCUENTA Y NUEVE");
		cardinal.put(new Integer(60),"SESENTA");
		cardinal.put(new Integer(61),"SESENTA Y UN");
		cardinal.put(new Integer(62),"SESENTA Y DOS");
		cardinal.put(new Integer(63),"SESENTA Y TRES");
		cardinal.put(new Integer(64),"SESENTA Y CUATRO");
		cardinal.put(new Integer(65),"SESENTA Y CINCO");
		cardinal.put(new Integer(66),"SESENTA Y SEIS");
		cardinal.put(new Integer(67),"SESENTA Y SIETE");
		cardinal.put(new Integer(68),"SESENTA Y OCHO");
		cardinal.put(new Integer(69),"SESENTA Y NUEVE");
		cardinal.put(new Integer(70),"SETENTA");
		cardinal.put(new Integer(71),"SETENTA Y UN");
		cardinal.put(new Integer(72),"SETENTA Y DOS");
		cardinal.put(new Integer(73),"SETENTA Y TRES");
		cardinal.put(new Integer(74),"SETENTA Y CUATRO");
		cardinal.put(new Integer(75),"SETENTA Y CINCO");
		cardinal.put(new Integer(76),"SETENTA Y SEIS");
		cardinal.put(new Integer(77),"SETENTA Y SIETE");
		cardinal.put(new Integer(78),"SETENTA Y OCHO");
		cardinal.put(new Integer(79),"SETENTA Y NUEVE");
		cardinal.put(new Integer(80),"OCHENTA");
		cardinal.put(new Integer(81),"OCHENTA Y UN");
		cardinal.put(new Integer(82),"OCHENTA Y DOS");
		cardinal.put(new Integer(83),"OCHENTA Y TRES");
		cardinal.put(new Integer(84),"OCHENTA Y CUATRO");
		cardinal.put(new Integer(85),"OCHENTA Y CINCO");
		cardinal.put(new Integer(86),"OCHENTA Y SEIS");
		cardinal.put(new Integer(87),"OCHENTA Y SIETE");
		cardinal.put(new Integer(88),"OCHENTA Y OCHO");
		cardinal.put(new Integer(89),"OCHENTA Y NUEVE");
		cardinal.put(new Integer(90),"NOVENTA");
		cardinal.put(new Integer(91),"NOVENTA Y UN");
		cardinal.put(new Integer(92),"NOVENTA Y DOS");
		cardinal.put(new Integer(93),"NOVENTA Y TRES");
		cardinal.put(new Integer(94),"NOVENTA Y CUATRO");
		cardinal.put(new Integer(95),"NOVENTA Y CINCO");
		cardinal.put(new Integer(96),"NOVENTA Y SEIS");
		cardinal.put(new Integer(97),"NOVENTA Y SIETE");
		cardinal.put(new Integer(98),"NOVENTA Y OCHO");
		cardinal.put(new Integer(99),"NOVENTA Y NUEVE");
		cardinal.put(new Integer(100),"CIEN");
		cardinal.put(new Integer(101),"CIENTO UN");
		cardinal.put(new Integer(102),"CIENTO DOS");
		cardinal.put(new Integer(103),"CIENTO TRES");
		cardinal.put(new Integer(104),"CIENTO CUATRO");
		cardinal.put(new Integer(105),"CIENTO CINCO");
		cardinal.put(new Integer(106),"CIENTO SEIS");
		cardinal.put(new Integer(107),"CIENTO SIETE");
		cardinal.put(new Integer(108),"CIENTO OCHO");
		cardinal.put(new Integer(109),"CIENTO NUEVE");
		cardinal.put(new Integer(110),"CIENTO DIEZ");
		cardinal.put(new Integer(111),"CIENTO ONCE");
		cardinal.put(new Integer(112),"CIENTO DOCE");
		cardinal.put(new Integer(113),"CIENTO TRECE");
		cardinal.put(new Integer(114),"CIENTO CATORCE");
		cardinal.put(new Integer(115),"CIENTO QUINCE");
		cardinal.put(new Integer(116),"CIENTO DIECISEIS");
		cardinal.put(new Integer(117),"CIENTO DIECISIETE");
		cardinal.put(new Integer(118),"CIENTO DIECIOCHO");
		cardinal.put(new Integer(119),"CIENTO DIECINUEVE");
		cardinal.put(new Integer(120),"CIENTO VEINTE");
		cardinal.put(new Integer(121),"CIENTO VEINTIUN");
		cardinal.put(new Integer(122),"CIENTO VEINTIDOS");
		cardinal.put(new Integer(123),"CIENTO VEINTITRES");
		cardinal.put(new Integer(124),"CIENTO VEINTICUATRO");
		cardinal.put(new Integer(125),"CIENTO VEINTICINCO");
		cardinal.put(new Integer(126),"CIENTO VEINTISEIS");
		cardinal.put(new Integer(127),"CIENTO VEINTISIETE");
		cardinal.put(new Integer(128),"CIENTO VEINTIOCHO");
		cardinal.put(new Integer(129),"CIENTO VEINTINUEVE");
		cardinal.put(new Integer(130),"CIENTO TREINTA");
		cardinal.put(new Integer(131),"CIENTO TREINTA Y UN");
		cardinal.put(new Integer(132),"CIENTO TREINTA Y DOS");
		cardinal.put(new Integer(133),"CIENTO TREINTA Y TRES");
		cardinal.put(new Integer(134),"CIENTO TREINTA Y CUATRO");
		cardinal.put(new Integer(135),"CIENTO TREINTA Y CINCO");
		cardinal.put(new Integer(136),"CIENTO TREINTA Y SEIS");
		cardinal.put(new Integer(137),"CIENTO TREINTA Y SIETE");
		cardinal.put(new Integer(138),"CIENTO TREINTA Y OCHO");
		cardinal.put(new Integer(139),"CIENTO TREINTA Y NUEVE");
		cardinal.put(new Integer(140),"CIENTO CUARENTA");
		cardinal.put(new Integer(141),"CIENTO CUARENTA Y UN");
		cardinal.put(new Integer(142),"CIENTO CUARENTA Y DOS");
		cardinal.put(new Integer(143),"CIENTO CUARENTA Y TRES");
		cardinal.put(new Integer(144),"CIENTO CUARENTA Y CUATRO");
		cardinal.put(new Integer(145),"CIENTO CUARENTA Y CINCO");
		cardinal.put(new Integer(146),"CIENTO CUARENTA Y SEIS");
		cardinal.put(new Integer(147),"CIENTO CUARENTA Y SIETE");
		cardinal.put(new Integer(148),"CIENTO CUARENTA Y OCHO");
		cardinal.put(new Integer(149),"CIENTO CUARENTA Y NUEVE");
		cardinal.put(new Integer(150),"CIENTO CINCUENTA");
		cardinal.put(new Integer(151),"CIENTO CINCUENTA Y UN");
		cardinal.put(new Integer(152),"CIENTO CINCUENTA Y DOS");
		cardinal.put(new Integer(153),"CIENTO CINCUENTA Y TRES");
		cardinal.put(new Integer(154),"CIENTO CINCUENTA Y CUATRO");
		cardinal.put(new Integer(155),"CIENTO CINCUENTA Y CINCO");
		cardinal.put(new Integer(156),"CIENTO CINCUENTA Y SEIS");
		cardinal.put(new Integer(157),"CIENTO CINCUENTA Y SIETE");
		cardinal.put(new Integer(158),"CIENTO CINCUENTA Y OCHO");
		cardinal.put(new Integer(159),"CIENTO CINCUENTA Y NUEVE");
		cardinal.put(new Integer(160),"CIENTO SESENTA");
		cardinal.put(new Integer(161),"CIENTO SESENTA Y UN");
		cardinal.put(new Integer(162),"CIENTO SESENTA Y DOS");
		cardinal.put(new Integer(163),"CIENTO SESENTA Y TRES");
		cardinal.put(new Integer(164),"CIENTO SESENTA Y CUATRO");
		cardinal.put(new Integer(165),"CIENTO SESENTA Y CINCO");
		cardinal.put(new Integer(166),"CIENTO SESENTA Y SEIS");
		cardinal.put(new Integer(167),"CIENTO SESENTA Y SIETE");
		cardinal.put(new Integer(168),"CIENTO SESENTA Y OCHO");
		cardinal.put(new Integer(169),"CIENTO SESENTA Y NUEVE");
		cardinal.put(new Integer(170),"CIENTO SETENTA");
		cardinal.put(new Integer(171),"CIENTO SETENTA Y UN");
		cardinal.put(new Integer(172),"CIENTO SETENTA Y DOS");
		cardinal.put(new Integer(173),"CIENTO SETENTA Y TRES");
		cardinal.put(new Integer(174),"CIENTO SETENTA Y CUATRO");
		cardinal.put(new Integer(175),"CIENTO SETENTA Y CINCO");
		cardinal.put(new Integer(176),"CIENTO SETENTA Y SEIS");
		cardinal.put(new Integer(177),"CIENTO SETENTA Y SIETE");
		cardinal.put(new Integer(178),"CIENTO SETENTA Y OCHO");
		cardinal.put(new Integer(179),"CIENTO SETENTA Y NUEVE");
		cardinal.put(new Integer(180),"CIENTO OCHENTA");
		cardinal.put(new Integer(181),"CIENTO OCHENTA Y UN");
		cardinal.put(new Integer(182),"CIENTO OCHENTA Y DOS");
		cardinal.put(new Integer(183),"CIENTO OCHENTA Y TRES");
		cardinal.put(new Integer(184),"CIENTO OCHENTA Y CUATRO");
		cardinal.put(new Integer(185),"CIENTO OCHENTA Y CINCO");
		cardinal.put(new Integer(186),"CIENTO OCHENTA Y SEIS");
		cardinal.put(new Integer(187),"CIENTO OCHENTA Y SIETE");
		cardinal.put(new Integer(188),"CIENTO OCHENTA Y OCHO");
		cardinal.put(new Integer(189),"CIENTO OCHENTA Y NUEVE");
		cardinal.put(new Integer(190),"CIENTO NOVENTA");
		cardinal.put(new Integer(191),"CIENTO NOVENTA Y UN");
		cardinal.put(new Integer(192),"CIENTO NOVENTA Y DOS");
		cardinal.put(new Integer(193),"CIENTO NOVENTA Y TRES");
		cardinal.put(new Integer(194),"CIENTO NOVENTA Y CUATRO");
		cardinal.put(new Integer(195),"CIENTO NOVENTA Y CINCO");
		cardinal.put(new Integer(196),"CIENTO NOVENTA Y SEIS");
		cardinal.put(new Integer(197),"CIENTO NOVENTA Y SIETE");
		cardinal.put(new Integer(198),"CIENTO NOVENTA Y OCHO");
		cardinal.put(new Integer(199),"CIENTO NOVENTA Y NUEVE");
		cardinal.put(new Integer(200),"DOSCIENTOS");
		cardinal.put(new Integer(201),"DOSCIENTOS UN");
		cardinal.put(new Integer(202),"DOSCIENTOS DOS");
		cardinal.put(new Integer(203),"DOSCIENTOS TRES");
		cardinal.put(new Integer(204),"DOSCIENTOS CUATRO");
		cardinal.put(new Integer(205),"DOSCIENTOS CINCO");
		cardinal.put(new Integer(206),"DOSCIENTOS SEIS");
		cardinal.put(new Integer(207),"DOSCIENTOS SIETE");
		cardinal.put(new Integer(208),"DOSCIENTOS OCHO");
		cardinal.put(new Integer(209),"DOSCIENTOS NUEVE");
		cardinal.put(new Integer(210),"DOSCIENTOS DIEZ");
		cardinal.put(new Integer(211),"DOSCIENTOS ONCE");
		cardinal.put(new Integer(212),"DOSCIENTOS DOCE");
		cardinal.put(new Integer(213),"DOSCIENTOS TRECE");
		cardinal.put(new Integer(214),"DOSCIENTOS CATORCE");
		cardinal.put(new Integer(215),"DOSCIENTOS QUINCE");
		cardinal.put(new Integer(216),"DOSCIENTOS DIECISEIS");
		cardinal.put(new Integer(217),"DOSCIENTOS DIECISIETE");
		cardinal.put(new Integer(218),"DOSCIENTOS DIECIOCHO");
		cardinal.put(new Integer(219),"DOSCIENTOS DIECINUEVE");
		cardinal.put(new Integer(220),"DOSCIENTOS VEINTE");
		cardinal.put(new Integer(221),"DOSCIENTOS VEINTIUN");
		cardinal.put(new Integer(222),"DOSCIENTOS VEINTIDOS");
		cardinal.put(new Integer(223),"DOSCIENTOS VEINTITRES");
		cardinal.put(new Integer(224),"DOSCIENTOS VEINTICUATRO");
		cardinal.put(new Integer(225),"DOSCIENTOS VEINTICINCO");
		cardinal.put(new Integer(226),"DOSCIENTOS VEINTISEIS");
		cardinal.put(new Integer(227),"DOSCIENTOS VEINTISIETE");
		cardinal.put(new Integer(228),"DOSCIENTOS VEINTIOCHO");
		cardinal.put(new Integer(229),"DOSCIENTOS VEINTINUEVE");
		cardinal.put(new Integer(230),"DOSCIENTOS TREINTA");
		cardinal.put(new Integer(231),"DOSCIENTOS TREINTA Y UN");
		cardinal.put(new Integer(232),"DOSCIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(233),"DOSCIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(234),"DOSCIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(235),"DOSCIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(236),"DOSCIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(237),"DOSCIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(238),"DOSCIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(239),"DOSCIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(240),"DOSCIENTOS CUARENTA");
		cardinal.put(new Integer(241),"DOSCIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(242),"DOSCIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(243),"DOSCIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(244),"DOSCIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(245),"DOSCIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(246),"DOSCIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(247),"DOSCIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(248),"DOSCIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(249),"DOSCIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(250),"DOSCIENTOS CINCUENTA");
		cardinal.put(new Integer(251),"DOSCIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(252),"DOSCIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(253),"DOSCIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(254),"DOSCIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(255),"DOSCIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(256),"DOSCIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(257),"DOSCIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(258),"DOSCIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(259),"DOSCIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(260),"DOSCIENTOS SESENTA");
		cardinal.put(new Integer(261),"DOSCIENTOS SESENTA Y UN");
		cardinal.put(new Integer(262),"DOSCIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(263),"DOSCIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(264),"DOSCIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(265),"DOSCIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(266),"DOSCIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(267),"DOSCIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(268),"DOSCIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(269),"DOSCIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(270),"DOSCIENTOS SETENTA");
		cardinal.put(new Integer(271),"DOSCIENTOS SETENTA Y UN");
		cardinal.put(new Integer(272),"DOSCIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(273),"DOSCIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(274),"DOSCIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(275),"DOSCIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(276),"DOSCIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(277),"DOSCIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(278),"DOSCIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(279),"DOSCIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(280),"DOSCIENTOS OCHENTA");
		cardinal.put(new Integer(281),"DOSCIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(282),"DOSCIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(283),"DOSCIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(284),"DOSCIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(285),"DOSCIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(286),"DOSCIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(287),"DOSCIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(288),"DOSCIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(289),"DOSCIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(290),"DOSCIENTOS NOVENTA");
		cardinal.put(new Integer(291),"DOSCIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(292),"DOSCIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(293),"DOSCIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(294),"DOSCIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(295),"DOSCIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(296),"DOSCIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(297),"DOSCIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(298),"DOSCIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(299),"DOSCIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(300),"TRESCIENTOS");
		cardinal.put(new Integer(301),"TRESCIENTOS UN");
		cardinal.put(new Integer(302),"TRESCIENTOS DOS");
		cardinal.put(new Integer(303),"TRESCIENTOS TRES");
		cardinal.put(new Integer(304),"TRESCIENTOS CUATRO");
		cardinal.put(new Integer(305),"TRESCIENTOS CINCO");
		cardinal.put(new Integer(306),"TRESCIENTOS SEIS");
		cardinal.put(new Integer(307),"TRESCIENTOS SIETE");
		cardinal.put(new Integer(308),"TRESCIENTOS OCHO");
		cardinal.put(new Integer(309),"TRESCIENTOS NUEVE");
		cardinal.put(new Integer(310),"TRESCIENTOS DIEZ");
		cardinal.put(new Integer(311),"TRESCIENTOS ONCE");
		cardinal.put(new Integer(312),"TRESCIENTOS DOCE");
		cardinal.put(new Integer(313),"TRESCIENTOS TRECE");
		cardinal.put(new Integer(314),"TRESCIENTOS CATORCE");
		cardinal.put(new Integer(315),"TRESCIENTOS QUINCE");
		cardinal.put(new Integer(316),"TRESCIENTOS DIECISEIS");
		cardinal.put(new Integer(317),"TRESCIENTOS DIECISIETE");
		cardinal.put(new Integer(318),"TRESCIENTOS DIECIOCHO");
		cardinal.put(new Integer(319),"TRESCIENTOS DIECINUEVE");
		cardinal.put(new Integer(320),"TRESCIENTOS VEINTE");
		cardinal.put(new Integer(321),"TRESCIENTOS VEINTIUN");
		cardinal.put(new Integer(322),"TRESCIENTOS VEINTIDOS");
		cardinal.put(new Integer(323),"TRESCIENTOS VEINTITRES");
		cardinal.put(new Integer(324),"TRESCIENTOS VEINTICUATRO");
		cardinal.put(new Integer(325),"TRESCIENTOS VEINTICINCO");
		cardinal.put(new Integer(326),"TRESCIENTOS VEINTISEIS");
		cardinal.put(new Integer(327),"TRESCIENTOS VEINTISIETE");
		cardinal.put(new Integer(328),"TRESCIENTOS VEINTIOCHO");
		cardinal.put(new Integer(329),"TRESCIENTOS VEINTINUEVE");
		cardinal.put(new Integer(330),"TRESCIENTOS TREINTA");
		cardinal.put(new Integer(331),"TRESCIENTOS TREINTA Y UN");
		cardinal.put(new Integer(332),"TRESCIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(333),"TRESCIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(334),"TRESCIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(335),"TRESCIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(336),"TRESCIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(337),"TRESCIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(338),"TRESCIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(339),"TRESCIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(340),"TRESCIENTOS CUARENTA");
		cardinal.put(new Integer(341),"TRESCIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(342),"TRESCIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(343),"TRESCIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(344),"TRESCIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(345),"TRESCIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(346),"TRESCIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(347),"TRESCIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(348),"TRESCIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(349),"TRESCIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(350),"TRESCIENTOS CINCUENTA");
		cardinal.put(new Integer(351),"TRESCIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(352),"TRESCIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(353),"TRESCIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(354),"TRESCIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(355),"TRESCIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(356),"TRESCIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(357),"TRESCIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(358),"TRESCIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(359),"TRESCIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(360),"TRESCIENTOS SESENTA");
		cardinal.put(new Integer(361),"TRESCIENTOS SESENTA Y UN");
		cardinal.put(new Integer(362),"TRESCIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(363),"TRESCIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(364),"TRESCIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(365),"TRESCIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(366),"TRESCIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(367),"TRESCIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(368),"TRESCIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(369),"TRESCIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(370),"TRESCIENTOS SETENTA");
		cardinal.put(new Integer(371),"TRESCIENTOS SETENTA Y UN");
		cardinal.put(new Integer(372),"TRESCIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(373),"TRESCIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(374),"TRESCIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(375),"TRESCIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(376),"TRESCIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(377),"TRESCIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(378),"TRESCIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(379),"TRESCIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(380),"TRESCIENTOS OCHENTA");
		cardinal.put(new Integer(381),"TRESCIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(382),"TRESCIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(383),"TRESCIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(384),"TRESCIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(385),"TRESCIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(386),"TRESCIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(387),"TRESCIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(388),"TRESCIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(389),"TRESCIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(390),"TRESCIENTOS NOVENTA");
		cardinal.put(new Integer(391),"TRESCIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(392),"TRESCIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(393),"TRESCIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(394),"TRESCIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(395),"TRESCIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(396),"TRESCIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(397),"TRESCIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(398),"TRESCIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(399),"TRESCIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(400),"CUATROCIENTOS");
		cardinal.put(new Integer(401),"CUATROCIENTOS UN");
		cardinal.put(new Integer(402),"CUATROCIENTOS DOS");
		cardinal.put(new Integer(403),"CUATROCIENTOS TRES");
		cardinal.put(new Integer(404),"CUATROCIENTOS CUATRO");
		cardinal.put(new Integer(405),"CUATROCIENTOS CINCO");
		cardinal.put(new Integer(406),"CUATROCIENTOS SEIS");
		cardinal.put(new Integer(407),"CUATROCIENTOS SIETE");
		cardinal.put(new Integer(408),"CUATROCIENTOS OCHO");
		cardinal.put(new Integer(409),"CUATROCIENTOS NUEVE");
		cardinal.put(new Integer(410),"CUATROCIENTOS DIEZ");
		cardinal.put(new Integer(411),"CUATROCIENTOS ONCE");
		cardinal.put(new Integer(412),"CUATROCIENTOS DOCE");
		cardinal.put(new Integer(413),"CUATROCIENTOS TRECE");
		cardinal.put(new Integer(414),"CUATROCIENTOS CATORCE");
		cardinal.put(new Integer(415),"CUATROCIENTOS QUINCE");
		cardinal.put(new Integer(416),"CUATROCIENTOS DIECISEIS");
		cardinal.put(new Integer(417),"CUATROCIENTOS DIECISIETE");
		cardinal.put(new Integer(418),"CUATROCIENTOS DIECIOCHO");
		cardinal.put(new Integer(419),"CUATROCIENTOS DIECINUEVE");
		cardinal.put(new Integer(420),"CUATROCIENTOS VEINTE");
		cardinal.put(new Integer(421),"CUATROCIENTOS VEINTIUN");
		cardinal.put(new Integer(422),"CUATROCIENTOS VEINTIDOS");
		cardinal.put(new Integer(423),"CUATROCIENTOS VEINTITRES");
		cardinal.put(new Integer(424),"CUATROCIENTOS VEINTICUATRO");
		cardinal.put(new Integer(425),"CUATROCIENTOS VEINTICINCO");
		cardinal.put(new Integer(426),"CUATROCIENTOS VEINTISEIS");
		cardinal.put(new Integer(427),"CUATROCIENTOS VEINTISIETE");
		cardinal.put(new Integer(428),"CUATROCIENTOS VEINTIOCHO");
		cardinal.put(new Integer(429),"CUATROCIENTOS VEINTINUEVE");
		cardinal.put(new Integer(430),"CUATROCIENTOS TREINTA");
		cardinal.put(new Integer(431),"CUATROCIENTOS TREINTA Y UN");
		cardinal.put(new Integer(432),"CUATROCIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(433),"CUATROCIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(434),"CUATROCIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(435),"CUATROCIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(436),"CUATROCIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(437),"CUATROCIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(438),"CUATROCIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(439),"CUATROCIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(440),"CUATROCIENTOS CUARENTA");
		cardinal.put(new Integer(441),"CUATROCIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(442),"CUATROCIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(443),"CUATROCIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(444),"CUATROCIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(445),"CUATROCIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(446),"CUATROCIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(447),"CUATROCIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(448),"CUATROCIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(449),"CUATROCIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(450),"CUATROCIENTOS CINCUENTA");
		cardinal.put(new Integer(451),"CUATROCIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(452),"CUATROCIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(453),"CUATROCIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(454),"CUATROCIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(455),"CUATROCIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(456),"CUATROCIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(457),"CUATROCIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(458),"CUATROCIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(459),"CUATROCIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(460),"CUATROCIENTOS SESENTA");
		cardinal.put(new Integer(461),"CUATROCIENTOS SESENTA Y UN");
		cardinal.put(new Integer(462),"CUATROCIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(463),"CUATROCIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(464),"CUATROCIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(465),"CUATROCIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(466),"CUATROCIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(467),"CUATROCIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(468),"CUATROCIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(469),"CUATROCIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(470),"CUATROCIENTOS SETENTA");
		cardinal.put(new Integer(471),"CUATROCIENTOS SETENTA Y UN");
		cardinal.put(new Integer(472),"CUATROCIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(473),"CUATROCIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(474),"CUATROCIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(475),"CUATROCIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(476),"CUATROCIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(477),"CUATROCIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(478),"CUATROCIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(479),"CUATROCIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(480),"CUATROCIENTOS OCHENTA");
		cardinal.put(new Integer(481),"CUATROCIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(482),"CUATROCIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(483),"CUATROCIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(484),"CUATROCIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(485),"CUATROCIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(486),"CUATROCIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(487),"CUATROCIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(488),"CUATROCIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(489),"CUATROCIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(490),"CUATROCIENTOS NOVENTA");
		cardinal.put(new Integer(491),"CUATROCIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(492),"CUATROCIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(493),"CUATROCIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(494),"CUATROCIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(495),"CUATROCIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(496),"CUATROCIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(497),"CUATROCIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(498),"CUATROCIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(499),"CUATROCIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(500),"QUINIENTOS");
		cardinal.put(new Integer(502),"QUINIENTOS DOS");
		cardinal.put(new Integer(503),"QUINIENTOS TRES");
		cardinal.put(new Integer(504),"QUINIENTOS CUATRO");
		cardinal.put(new Integer(505),"QUINIENTOS CINCO");
		cardinal.put(new Integer(506),"QUINIENTOS SEIS");
		cardinal.put(new Integer(507),"QUINIENTOS SIETE");
		cardinal.put(new Integer(508),"QUINIENTOS OCHO");
		cardinal.put(new Integer(509),"QUINIENTOS NUEVE");
		cardinal.put(new Integer(510),"QUINIENTOS DIEZ");
		cardinal.put(new Integer(511),"QUINIENTOS ONCE");
		cardinal.put(new Integer(512),"QUINIENTOS DOCE");
		cardinal.put(new Integer(513),"QUINIENTOS TRECE");
		cardinal.put(new Integer(514),"QUINIENTOS CATORCE");
		cardinal.put(new Integer(515),"QUINIENTOS QUINCE");
		cardinal.put(new Integer(516),"QUINIENTOS DIECISEIS");
		cardinal.put(new Integer(517),"QUINIENTOS DIECISIETE");
		cardinal.put(new Integer(518),"QUINIENTOS DIECIOCHO");
		cardinal.put(new Integer(519),"QUINIENTOS DIECINUEVE");
		cardinal.put(new Integer(520),"QUINIENTOS VEINTE");
		cardinal.put(new Integer(521),"QUINIENTOS VEINTIUN");
		cardinal.put(new Integer(522),"QUINIENTOS VEINTIDOS");
		cardinal.put(new Integer(523),"QUINIENTOS VEINTITRES");
		cardinal.put(new Integer(524),"QUINIENTOS VEINTICUATRO");
		cardinal.put(new Integer(525),"QUINIENTOS VEINTICINCO");
		cardinal.put(new Integer(526),"QUINIENTOS VEINTISEIS");
		cardinal.put(new Integer(527),"QUINIENTOS VEINTISIETE");
		cardinal.put(new Integer(528),"QUINIENTOS VEINTIOCHO");
		cardinal.put(new Integer(529),"QUINIENTOS VEINTINUEVE");
		cardinal.put(new Integer(530),"QUINIENTOS TREINTA");
		cardinal.put(new Integer(531),"QUINIENTOS TREINTA Y UN");
		cardinal.put(new Integer(532),"QUINIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(533),"QUINIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(534),"QUINIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(535),"QUINIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(536),"QUINIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(537),"QUINIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(538),"QUINIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(539),"QUINIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(540),"QUINIENTOS CUARENTA");
		cardinal.put(new Integer(541),"QUINIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(542),"QUINIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(543),"QUINIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(544),"QUINIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(545),"QUINIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(546),"QUINIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(547),"QUINIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(548),"QUINIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(549),"QUINIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(550),"QUINIENTOS CINCUENTA");
		cardinal.put(new Integer(551),"QUINIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(552),"QUINIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(553),"QUINIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(554),"QUINIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(555),"QUINIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(556),"QUINIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(557),"QUINIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(558),"QUINIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(559),"QUINIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(560),"QUINIENTOS SESENTA");
		cardinal.put(new Integer(561),"QUINIENTOS SESENTA Y UN");
		cardinal.put(new Integer(562),"QUINIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(563),"QUINIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(564),"QUINIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(565),"QUINIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(566),"QUINIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(567),"QUINIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(568),"QUINIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(569),"QUINIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(570),"QUINIENTOS SETENTA");
		cardinal.put(new Integer(571),"QUINIENTOS SETENTA Y UN");
		cardinal.put(new Integer(572),"QUINIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(573),"QUINIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(574),"QUINIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(575),"QUINIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(576),"QUINIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(577),"QUINIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(578),"QUINIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(579),"QUINIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(580),"QUINIENTOS OCHENTA");
		cardinal.put(new Integer(581),"QUINIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(582),"QUINIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(583),"QUINIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(584),"QUINIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(585),"QUINIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(586),"QUINIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(587),"QUINIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(588),"QUINIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(589),"QUINIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(590),"QUINIENTOS NOVENTA");
		cardinal.put(new Integer(591),"QUINIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(592),"QUINIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(593),"QUINIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(594),"QUINIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(595),"QUINIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(596),"QUINIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(597),"QUINIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(598),"QUINIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(599),"QUINIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(600),"SEISCIENTOS");
		cardinal.put(new Integer(601),"SEISCIENTOS UN");
		cardinal.put(new Integer(602),"SEISCIENTOS DOS");
		cardinal.put(new Integer(603),"SEISCIENTOS TRES");
		cardinal.put(new Integer(604),"SEISCIENTOS CUATRO");
		cardinal.put(new Integer(605),"SEISCIENTOS CINCO");
		cardinal.put(new Integer(606),"SEISCIENTOS SEIS");
		cardinal.put(new Integer(607),"SEISCIENTOS SIETE");
		cardinal.put(new Integer(608),"SEISCIENTOS OCHO");
		cardinal.put(new Integer(609),"SEISCIENTOS NUEVE");
		cardinal.put(new Integer(610),"SEISCIENTOS DIEZ");
		cardinal.put(new Integer(611),"SEISCIENTOS ONCE");
		cardinal.put(new Integer(612),"SEISCIENTOS DOCE");
		cardinal.put(new Integer(613),"SEISCIENTOS TRECE");
		cardinal.put(new Integer(614),"SEISCIENTOS CATORCE");
		cardinal.put(new Integer(615),"SEISCIENTOS QUINCE");
		cardinal.put(new Integer(616),"SEISCIENTOS DIECISEIS");
		cardinal.put(new Integer(617),"SEISCIENTOS DIECISIETE");
		cardinal.put(new Integer(618),"SEISCIENTOS DIECIOCHO");
		cardinal.put(new Integer(619),"SEISCIENTOS DIECINUEVE");
		cardinal.put(new Integer(620),"SEISCIENTOS VEINTE");
		cardinal.put(new Integer(621),"SEISCIENTOS VEINTIUN");
		cardinal.put(new Integer(622),"SEISCIENTOS VEINTIDOS");
		cardinal.put(new Integer(623),"SEISCIENTOS VEINTITRES");
		cardinal.put(new Integer(624),"SEISCIENTOS VEINTICUATRO");
		cardinal.put(new Integer(625),"SEISCIENTOS VEINTICINCO");
		cardinal.put(new Integer(626),"SEISCIENTOS VEINTISEIS");
		cardinal.put(new Integer(627),"SEISCIENTOS VEINTISIETE");
		cardinal.put(new Integer(628),"SEISCIENTOS VEINTIOCHO");
		cardinal.put(new Integer(629),"SEISCIENTOS VEINTINUEVE");
		cardinal.put(new Integer(630),"SEISCIENTOS TREINTA");
		cardinal.put(new Integer(631),"SEISCIENTOS TREINTA Y UN");
		cardinal.put(new Integer(632),"SEISCIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(633),"SEISCIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(634),"SEISCIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(635),"SEISCIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(636),"SEISCIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(637),"SEISCIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(638),"SEISCIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(639),"SEISCIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(640),"SEISCIENTOS CUARENTA");
		cardinal.put(new Integer(641),"SEISCIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(642),"SEISCIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(643),"SEISCIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(644),"SEISCIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(645),"SEISCIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(646),"SEISCIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(647),"SEISCIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(648),"SEISCIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(649),"SEISCIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(650),"SEISCIENTOS CINCUENTA");
		cardinal.put(new Integer(651),"SEISCIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(652),"SEISCIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(653),"SEISCIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(654),"SEISCIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(655),"SEISCIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(656),"SEISCIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(657),"SEISCIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(658),"SEISCIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(659),"SEISCIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(660),"SEISCIENTOS SESENTA");
		cardinal.put(new Integer(661),"SEISCIENTOS SESENTA Y UN");
		cardinal.put(new Integer(662),"SEISCIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(663),"SEISCIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(664),"SEISCIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(665),"SEISCIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(666),"SEISCIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(667),"SEISCIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(668),"SEISCIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(669),"SEISCIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(670),"SEISCIENTOS SETENTA");
		cardinal.put(new Integer(671),"SEISCIENTOS SETENTA Y UN");
		cardinal.put(new Integer(672),"SEISCIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(673),"SEISCIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(674),"SEISCIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(675),"SEISCIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(676),"SEISCIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(677),"SEISCIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(678),"SEISCIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(679),"SEISCIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(680),"SEISCIENTOS OCHENTA");
		cardinal.put(new Integer(681),"SEISCIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(682),"SEISCIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(683),"SEISCIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(684),"SEISCIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(685),"SEISCIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(686),"SEISCIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(687),"SEISCIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(688),"SEISCIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(689),"SEISCIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(690),"SEISCIENTOS NOVENTA");
		cardinal.put(new Integer(691),"SEISCIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(692),"SEISCIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(693),"SEISCIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(694),"SEISCIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(695),"SEISCIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(696),"SEISCIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(697),"SEISCIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(698),"SEISCIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(699),"SEISCIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(700),"SETECIENTOS");
		cardinal.put(new Integer(701),"SETECIENTOS UN");
		cardinal.put(new Integer(702),"SETECIENTOS DOS");
		cardinal.put(new Integer(703),"SETECIENTOS TRES");
		cardinal.put(new Integer(704),"SETECIENTOS CUATRO");
		cardinal.put(new Integer(705),"SETECIENTOS CINCO");
		cardinal.put(new Integer(706),"SETECIENTOS SEIS");
		cardinal.put(new Integer(707),"SETECIENTOS SIETE");
		cardinal.put(new Integer(708),"SETECIENTOS OCHO");
		cardinal.put(new Integer(709),"SETECIENTOS NUEVE");
		cardinal.put(new Integer(710),"SETECIENTOS DIEZ");
		cardinal.put(new Integer(711),"SETECIENTOS ONCE");
		cardinal.put(new Integer(712),"SETECIENTOS DOCE");
		cardinal.put(new Integer(713),"SETECIENTOS TRECE");
		cardinal.put(new Integer(714),"SETECIENTOS CATORCE");
		cardinal.put(new Integer(715),"SETECIENTOS QUINCE");
		cardinal.put(new Integer(716),"SETECIENTOS DIECISEIS");
		cardinal.put(new Integer(717),"SETECIENTOS DIECISIETE");
		cardinal.put(new Integer(718),"SETECIENTOS DIECIOCHO");
		cardinal.put(new Integer(719),"SETECIENTOS DIECINUEVE");
		cardinal.put(new Integer(720),"SETECIENTOS VEINTE");
		cardinal.put(new Integer(721),"SETECIENTOS VEINTIUN");
		cardinal.put(new Integer(722),"SETECIENTOS VEINTIDOS");
		cardinal.put(new Integer(723),"SETECIENTOS VEINTITRES");
		cardinal.put(new Integer(724),"SETECIENTOS VEINTICUATRO");
		cardinal.put(new Integer(725),"SETECIENTOS VEINTICINCO");
		cardinal.put(new Integer(726),"SETECIENTOS VEINTISEIS");
		cardinal.put(new Integer(727),"SETECIENTOS VEINTISIETE");
		cardinal.put(new Integer(728),"SETECIENTOS VEINTIOCHO");
		cardinal.put(new Integer(729),"SETECIENTOS VEINTINUEVE");
		cardinal.put(new Integer(730),"SETECIENTOS TREINTA");
		cardinal.put(new Integer(731),"SETECIENTOS TREINTA Y UN");
		cardinal.put(new Integer(732),"SETECIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(733),"SETECIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(734),"SETECIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(735),"SETECIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(736),"SETECIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(737),"SETECIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(738),"SETECIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(739),"SETECIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(740),"SETECIENTOS CUARENTA");
		cardinal.put(new Integer(741),"SETECIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(742),"SETECIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(743),"SETECIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(744),"SETECIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(745),"SETECIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(746),"SETECIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(747),"SETECIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(748),"SETECIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(749),"SETECIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(750),"SETECIENTOS CINCUENTA");
		cardinal.put(new Integer(751),"SETECIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(752),"SETECIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(753),"SETECIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(754),"SETECIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(755),"SETECIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(756),"SETECIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(757),"SETECIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(758),"SETECIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(759),"SETECIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(760),"SETECIENTOS SESENTA");
		cardinal.put(new Integer(761),"SETECIENTOS SESENTA Y UN");
		cardinal.put(new Integer(762),"SETECIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(763),"SETECIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(764),"SETECIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(765),"SETECIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(766),"SETECIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(767),"SETECIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(768),"SETECIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(769),"SETECIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(770),"SETECIENTOS SETENTA");
		cardinal.put(new Integer(771),"SETECIENTOS SETENTA Y UN");
		cardinal.put(new Integer(772),"SETECIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(773),"SETECIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(774),"SETECIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(775),"SETECIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(776),"SETECIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(777),"SETECIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(778),"SETECIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(779),"SETECIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(780),"SETECIENTOS OCHENTA");
		cardinal.put(new Integer(781),"SETECIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(782),"SETECIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(783),"SETECIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(784),"SETECIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(785),"SETECIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(786),"SETECIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(787),"SETECIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(788),"SETECIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(789),"SETECIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(790),"SETECIENTOS NOVENTA");
		cardinal.put(new Integer(791),"SETECIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(792),"SETECIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(793),"SETECIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(794),"SETECIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(795),"SETECIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(796),"SETECIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(797),"SETECIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(798),"SETECIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(799),"SETECIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(800),"OCHOCIENTOS");
		cardinal.put(new Integer(801),"OCHOCIENTOS UN");
		cardinal.put(new Integer(802),"OCHOCIENTOS DOS");
		cardinal.put(new Integer(803),"OCHOCIENTOS TRES");
		cardinal.put(new Integer(804),"OCHOCIENTOS CUATRO");
		cardinal.put(new Integer(805),"OCHOCIENTOS CINCO");
		cardinal.put(new Integer(806),"OCHOCIENTOS SEIS");
		cardinal.put(new Integer(807),"OCHOCIENTOS SIETE");
		cardinal.put(new Integer(808),"OCHOCIENTOS OCHO");
		cardinal.put(new Integer(809),"OCHOCIENTOS NUEVE");
		cardinal.put(new Integer(810),"OCHOCIENTOS DIEZ");
		cardinal.put(new Integer(811),"OCHOCIENTOS ONCE");
		cardinal.put(new Integer(812),"OCHOCIENTOS DOCE");
		cardinal.put(new Integer(813),"OCHOCIENTOS TRECE");
		cardinal.put(new Integer(814),"OCHOCIENTOS CATORCE");
		cardinal.put(new Integer(815),"OCHOCIENTOS QUINCE");
		cardinal.put(new Integer(816),"OCHOCIENTOS DIECISEIS");
		cardinal.put(new Integer(817),"OCHOCIENTOS DIECISIETE");
		cardinal.put(new Integer(818),"OCHOCIENTOS DIECIOCHO");
		cardinal.put(new Integer(819),"OCHOCIENTOS DIECINUEVE");
		cardinal.put(new Integer(820),"OCHOCIENTOS VEINTE");
		cardinal.put(new Integer(821),"OCHOCIENTOS VEINTIUN");
		cardinal.put(new Integer(822),"OCHOCIENTOS VEINTIDOS");
		cardinal.put(new Integer(823),"OCHOCIENTOS VEINTITRES");
		cardinal.put(new Integer(824),"OCHOCIENTOS VEINTICUATRO");
		cardinal.put(new Integer(825),"OCHOCIENTOS VEINTICINCO");
		cardinal.put(new Integer(826),"OCHOCIENTOS VEINTISEIS");
		cardinal.put(new Integer(827),"OCHOCIENTOS VEINTISIETE");
		cardinal.put(new Integer(828),"OCHOCIENTOS VEINTIOCHO");
		cardinal.put(new Integer(829),"OCHOCIENTOS VEINTINUEVE");
		cardinal.put(new Integer(830),"OCHOCIENTOS TREINTA");
		cardinal.put(new Integer(831),"OCHOCIENTOS TREINTA Y UN");
		cardinal.put(new Integer(832),"OCHOCIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(833),"OCHOCIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(834),"OCHOCIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(835),"OCHOCIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(836),"OCHOCIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(837),"OCHOCIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(838),"OCHOCIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(839),"OCHOCIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(840),"OCHOCIENTOS CUARENTA");
		cardinal.put(new Integer(841),"OCHOCIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(842),"OCHOCIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(843),"OCHOCIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(844),"OCHOCIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(845),"OCHOCIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(846),"OCHOCIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(847),"OCHOCIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(848),"OCHOCIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(849),"OCHOCIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(850),"OCHOCIENTOS CINCUENTA");
		cardinal.put(new Integer(851),"OCHOCIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(852),"OCHOCIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(853),"OCHOCIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(854),"OCHOCIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(855),"OCHOCIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(856),"OCHOCIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(857),"OCHOCIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(858),"OCHOCIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(859),"OCHOCIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(860),"OCHOCIENTOS SESENTA");
		cardinal.put(new Integer(861),"OCHOCIENTOS SESENTA Y UN");
		cardinal.put(new Integer(862),"OCHOCIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(863),"OCHOCIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(864),"OCHOCIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(865),"OCHOCIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(866),"OCHOCIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(867),"OCHOCIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(868),"OCHOCIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(869),"OCHOCIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(870),"OCHOCIENTOS SETENTA");
		cardinal.put(new Integer(871),"OCHOCIENTOS SETENTA Y UN");
		cardinal.put(new Integer(872),"OCHOCIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(873),"OCHOCIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(874),"OCHOCIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(875),"OCHOCIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(876),"OCHOCIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(877),"OCHOCIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(878),"OCHOCIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(879),"OCHOCIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(880),"OCHOCIENTOS OCHENTA");
		cardinal.put(new Integer(881),"OCHOCIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(882),"OCHOCIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(883),"OCHOCIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(884),"OCHOCIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(885),"OCHOCIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(886),"OCHOCIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(887),"OCHOCIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(888),"OCHOCIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(889),"OCHOCIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(890),"OCHOCIENTOS NOVENTA");
		cardinal.put(new Integer(891),"OCHOCIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(892),"OCHOCIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(893),"OCHOCIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(894),"OCHOCIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(895),"OCHOCIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(896),"OCHOCIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(897),"OCHOCIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(898),"OCHOCIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(899),"OCHOCIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(900),"NOVECIENTOS");
		cardinal.put(new Integer(901),"NOVECIENTOS UN");
		cardinal.put(new Integer(902),"NOVECIENTOS DOS");
		cardinal.put(new Integer(903),"NOVECIENTOS TRES");
		cardinal.put(new Integer(904),"NOVECIENTOS CUATRO");
		cardinal.put(new Integer(905),"NOVECIENTOS CINCO");
		cardinal.put(new Integer(906),"NOVECIENTOS SEIS");
		cardinal.put(new Integer(907),"NOVECIENTOS SIETE");
		cardinal.put(new Integer(908),"NOVECIENTOS OCHO");
		cardinal.put(new Integer(909),"NOVECIENTOS NUEVE");
		cardinal.put(new Integer(910),"NOVECIENTOS DIEZ");
		cardinal.put(new Integer(911),"NOVECIENTOS ONCE");
		cardinal.put(new Integer(912),"NOVECIENTOS DOCE");
		cardinal.put(new Integer(913),"NOVECIENTOS TRECE");
		cardinal.put(new Integer(914),"NOVECIENTOS CATORCE");
		cardinal.put(new Integer(915),"NOVECIENTOS QUINCE");
		cardinal.put(new Integer(916),"NOVECIENTOS DIECISEIS");
		cardinal.put(new Integer(917),"NOVECIENTOS DIECISIETE");
		cardinal.put(new Integer(918),"NOVECIENTOS DIECIOCHO");
		cardinal.put(new Integer(919),"NOVECIENTOS DIECINUEVE");
		cardinal.put(new Integer(920),"NOVECIENTOS VEINTE");
		cardinal.put(new Integer(921),"NOVECIENTOS VEINTIUN");
		cardinal.put(new Integer(922),"NOVECIENTOS VEINTIDOS");
		cardinal.put(new Integer(923),"NOVECIENTOS VEINTITRES");
		cardinal.put(new Integer(924),"NOVECIENTOS VEINTICUATRO");
		cardinal.put(new Integer(925),"NOVECIENTOS VEINTICINCO");
		cardinal.put(new Integer(926),"NOVECIENTOS VEINTISEIS");
		cardinal.put(new Integer(927),"NOVECIENTOS VEINTISIETE");
		cardinal.put(new Integer(928),"NOVECIENTOS VEINTIOCHO");
		cardinal.put(new Integer(929),"NOVECIENTOS VEINTINUEVE");
		cardinal.put(new Integer(930),"NOVECIENTOS TREINTA");
		cardinal.put(new Integer(931),"NOVECIENTOS TREINTA Y UN");
		cardinal.put(new Integer(932),"NOVECIENTOS TREINTA Y DOS");
		cardinal.put(new Integer(933),"NOVECIENTOS TREINTA Y TRES");
		cardinal.put(new Integer(934),"NOVECIENTOS TREINTA Y CUATRO");
		cardinal.put(new Integer(935),"NOVECIENTOS TREINTA Y CINCO");
		cardinal.put(new Integer(936),"NOVECIENTOS TREINTA Y SEIS");
		cardinal.put(new Integer(937),"NOVECIENTOS TREINTA Y SIETE");
		cardinal.put(new Integer(938),"NOVECIENTOS TREINTA Y OCHO");
		cardinal.put(new Integer(939),"NOVECIENTOS TREINTA Y NUEVE");
		cardinal.put(new Integer(940),"NOVECIENTOS CUARENTA");
		cardinal.put(new Integer(941),"NOVECIENTOS CUARENTA Y UN");
		cardinal.put(new Integer(942),"NOVECIENTOS CUARENTA Y DOS");
		cardinal.put(new Integer(943),"NOVECIENTOS CUARENTA Y TRES");
		cardinal.put(new Integer(944),"NOVECIENTOS CUARENTA Y CUATRO");
		cardinal.put(new Integer(945),"NOVECIENTOS CUARENTA Y CINCO");
		cardinal.put(new Integer(946),"NOVECIENTOS CUARENTA Y SEIS");
		cardinal.put(new Integer(947),"NOVECIENTOS CUARENTA Y SIETE");
		cardinal.put(new Integer(948),"NOVECIENTOS CUARENTA Y OCHO");
		cardinal.put(new Integer(949),"NOVECIENTOS CUARENTA Y NUEVE");
		cardinal.put(new Integer(950),"NOVECIENTOS CINCUENTA");
		cardinal.put(new Integer(951),"NOVECIENTOS CINCUENTA Y UN");
		cardinal.put(new Integer(952),"NOVECIENTOS CINCUENTA Y DOS");
		cardinal.put(new Integer(953),"NOVECIENTOS CINCUENTA Y TRES");
		cardinal.put(new Integer(954),"NOVECIENTOS CINCUENTA Y CUATRO");
		cardinal.put(new Integer(955),"NOVECIENTOS CINCUENTA Y CINCO");
		cardinal.put(new Integer(956),"NOVECIENTOS CINCUENTA Y SEIS");
		cardinal.put(new Integer(957),"NOVECIENTOS CINCUENTA Y SIETE");
		cardinal.put(new Integer(958),"NOVECIENTOS CINCUENTA Y OCHO");
		cardinal.put(new Integer(959),"NOVECIENTOS CINCUENTA Y NUEVE");
		cardinal.put(new Integer(960),"NOVECIENTOS SESENTA");
		cardinal.put(new Integer(961),"NOVECIENTOS SESENTA Y UN");
		cardinal.put(new Integer(962),"NOVECIENTOS SESENTA Y DOS");
		cardinal.put(new Integer(963),"NOVECIENTOS SESENTA Y TRES");
		cardinal.put(new Integer(964),"NOVECIENTOS SESENTA Y CUATRO");
		cardinal.put(new Integer(965),"NOVECIENTOS SESENTA Y CINCO");
		cardinal.put(new Integer(966),"NOVECIENTOS SESENTA Y SEIS");
		cardinal.put(new Integer(967),"NOVECIENTOS SESENTA Y SIETE");
		cardinal.put(new Integer(968),"NOVECIENTOS SESENTA Y OCHO");
		cardinal.put(new Integer(969),"NOVECIENTOS SESENTA Y NUEVE");
		cardinal.put(new Integer(970),"NOVECIENTOS SETENTA");
		cardinal.put(new Integer(971),"NOVECIENTOS SETENTA Y UN");
		cardinal.put(new Integer(972),"NOVECIENTOS SETENTA Y DOS");
		cardinal.put(new Integer(973),"NOVECIENTOS SETENTA Y TRES");
		cardinal.put(new Integer(974),"NOVECIENTOS SETENTA Y CUATRO");
		cardinal.put(new Integer(975),"NOVECIENTOS SETENTA Y CINCO");
		cardinal.put(new Integer(976),"NOVECIENTOS SETENTA Y SEIS");
		cardinal.put(new Integer(977),"NOVECIENTOS SETENTA Y SIETE");
		cardinal.put(new Integer(978),"NOVECIENTOS SETENTA Y OCHO");
		cardinal.put(new Integer(979),"NOVECIENTOS SETENTA Y NUEVE");
		cardinal.put(new Integer(980),"NOVECIENTOS OCHENTA");
		cardinal.put(new Integer(981),"NOVECIENTOS OCHENTA Y UN");
		cardinal.put(new Integer(982),"NOVECIENTOS OCHENTA Y DOS");
		cardinal.put(new Integer(983),"NOVECIENTOS OCHENTA Y TRES");
		cardinal.put(new Integer(984),"NOVECIENTOS OCHENTA Y CUATRO");
		cardinal.put(new Integer(985),"NOVECIENTOS OCHENTA Y CINCO");
		cardinal.put(new Integer(986),"NOVECIENTOS OCHENTA Y SEIS");
		cardinal.put(new Integer(987),"NOVECIENTOS OCHENTA Y SIETE");
		cardinal.put(new Integer(988),"NOVECIENTOS OCHENTA Y OCHO");
		cardinal.put(new Integer(989),"NOVECIENTOS OCHENTA Y NUEVE");
		cardinal.put(new Integer(990),"NOVECIENTOS NOVENTA");
		cardinal.put(new Integer(991),"NOVECIENTOS NOVENTA Y UN");
		cardinal.put(new Integer(992),"NOVECIENTOS NOVENTA Y DOS");
		cardinal.put(new Integer(993),"NOVECIENTOS NOVENTA Y TRES");
		cardinal.put(new Integer(994),"NOVECIENTOS NOVENTA Y CUATRO");
		cardinal.put(new Integer(995),"NOVECIENTOS NOVENTA Y CINCO");
		cardinal.put(new Integer(996),"NOVECIENTOS NOVENTA Y SEIS");
		cardinal.put(new Integer(997),"NOVECIENTOS NOVENTA Y SIETE");
		cardinal.put(new Integer(998),"NOVECIENTOS NOVENTA Y OCHO");
		cardinal.put(new Integer(999),"NOVECIENTOS NOVENTA Y NUEVE");
		cardinal.put(new Integer(1000),"UN MIL");
	}
	
	/**
	 * @return the cardinalidRad
	 */
	public HashMap getCardinal() {
		return cardinal;
	}

	/**
	 * @param cardinalidRad the cardinalidRad to set
	 */
	public void setCardinal(HashMap cardinalidRad) {
		this.cardinal = cardinalidRad;
	}
	
	/**
	 * Convierte un n&uacute;mero en la cantidad que le corresponderia en letras.<br>
	 * El n&uacute;mero m&aacute;ximo que soporta es <b>999,999,999.99</b>, en dado caso
	 * si sobrepasa el n&uacute;mero m&aacute;ximo permitido la conversi&oacute;n se ignorara.
	 * @param numero n&uacute;mero el cual ser&aacute; convertido 
	 * @return La cantidad ingresada convertida en letras si esta en el m&aacute;ximo establecido,
	 * en caso contrario devuelve el valor de <i>'NO DEFINIDO'</i>. 
	 */
	
	public String getLetter(String numero){
		return getLetter(Double.parseDouble(numero));
	}
	
	/**
	 * Convierte un n&uacute;mero en la cantidad que le corresponderia en letras.<br>
	 * El n&uacute;mero m&aacute;ximo que soporta es <b>999,999,999.99</b>, en dado caso
	 * si sobrepasa el n&uacute;mero m&aacute;ximo permitido la conversi&oacute;n se ignorara.
	 * @param numero n&uacute;mero el cual ser&aacute; convertido 
	 * @return La cantidad ingresada convertida en letras si esta en el m&aacute;ximo establecido,
	 * en caso contrario devuelve el valor de <i>'NO DEFINIDO'</i>. 
	 */
	public String getLetter(int numero){
		return getLetter((double)numero);
	}
	
	/**
	 * Convierte un n&uacute;mero en la cantidad que le corresponderia en letras.<br>
	 * El n&uacute;mero m&aacute;ximo que soporta es <b>999,999,999.99</b>, en dado caso
	 * si sobrepasa el n&uacute;mero m&aacute;ximo permitido la conversi&oacute;n se ignorara.
	 * @param numero n&uacute;mero el cual ser&aacute; convertido 
	 * @return La cantidad ingresada convertida en letras si esta en el m&aacute;ximo establecido,
	 * en caso contrario devuelve el valor de <i>'NO DEFINIDO'</i>. 
	 */
	public String getLetter(long numero){
		return getLetter((double)numero);
	}
	
	/**
	 * Convierte un n&uacute;mero en la cantidad que le corresponderia en letras.<br>
	 * El n&uacute;mero m&aacute;ximo que soporta es <b>999,999,999.99</b>, en dado caso
	 * si sobrepasa el n&uacute;mero m&aacute;ximo permitido la conversi&oacute;n se ignorara.
	 * @param numero n&uacute;mero el cual ser&aacute; convertido 
	 * @return La cantidad ingresada convertida en letras si esta en el m&aacute;ximo establecido,
	 * en caso contrario devuelve el valor de <i>'NO DEFINIDO'</i>. 
	 */
	public String getLetter(float numero){
		return getLetter((double)numero);
	}
	
	/**
	 * Convierte un n&uacute;mero en la cantidad que le corresponderia en letras.<br>
	 * El n&uacute;mero m&aacute;ximo que soporta es <b>999,999,999.99</b>, en dado caso
	 * si sobrepasa el n&uacute;mero m&aacute;ximo permitido la conversi&oacute;n se ignorara.
	 * @param numero n&uacute;mero el cual ser&aacute; convertido 
	 * @return La cantidad ingresada convertida en letras si esta en el m&aacute;ximo establecido,
	 * en caso contrario devuelve el valor de <i>'NO DEFINIDO'</i>. 
	 */
	public String getLetter(double numero){
		String letter = "CERO";
		if(numero<=999999999.99){
//			sacamos los millones
			int millones = (int)(numero/1000000);
			//sacamos los miles
			int miles = (int)((numero/1000)-(((int)(numero/1000000)))*1000);
			//sacamos lo demas :P
			int centenas = (int)(((int)(numero/1))-(((int)(numero/1000))*1000));
			
			if(millones>0){
				letter = (String)cardinal.get(new Integer(millones))+(millones==1?" MILLON ":" MILLONES ");
			}
			
			if(letter.equals("CERO")&&miles>0){
				letter = (String)cardinal.get(new Integer(miles)) + " MIL ";
			}else if(miles>0){
				letter += (String)cardinal.get(new Integer(miles)) + " MIL ";
			}
			
			if(letter.equals("CERO")&&centenas>0){
				letter=(String)cardinal.get(new Integer(centenas))+" ";
			}else if(centenas>0){
				letter += (String)cardinal.get(new Integer(centenas))+ " ";
			}
			
			if(numero>0){
				//modificación 20 julio 2007,no se realiza de manera efectiva la aproximación
				//int centavos =(int)((aproximar(numero-((int)numero),2))*100); 
				//letter += ((centavos==0)?"00":centavos+"")+"/100"; 
				DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("es","sv"));
				DecimalFormat format = new DecimalFormat("##0",symbols);
				double valor=aproximar(numero-((int)numero),2)*100;
				String valorCadena = format.format(valor);
				letter += ((valorCadena.equals("0"))?"00":valorCadena+"")+"/100";
			}
		}else{
			letter="NO DEFINIDO";
		}
		
		return letter;
	}
	
	/**
	 * Aproxima una cantidad n a x cantidad de cifras decimales
	 * @param valor cantidad que ser&aacute; aproximada
	 * @param cifras cantidad de cifras que se desean que aproximen
	 * @return la cantidad aproximada
	 */
	public double aproximar(String valor, int cifras){
		return aproximar(Double.parseDouble(valor), cifras);
	}
	
	/**
	 * Aproxima una cantidad n a x cantidad de cifras decimales
	 * @param valor cantidad que ser&aacute; aproximada
	 * @param cifras cantidad de cifras que se desean que aproximen
	 * @return la cantidad aproximada
	 */
	public double aproximar(double valor, int cifras){
		//modificación 20 julio 2007,no se realiza de manera efectiva la aproximación
		/*double aproc=5/(Math.pow(10, (double)cifras+1));
		double divisor = (Math.pow(10, (double)cifras));
		return ((int)((valor+aproc)*divisor))/divisor;*/
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("es","sv"));
		DecimalFormat format = new DecimalFormat("##0.00",symbols);
		String valorCadena = format.format(valor);
		return Double.parseDouble(valorCadena);
		
	}
	
	public static void main(String[] args){
		Number2Letter n=new Number2Letter();
		
		System.out.println(n.getLetter("1484.58") );
	}
	
}