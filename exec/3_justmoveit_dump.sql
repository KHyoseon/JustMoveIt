CREATE DATABASE  IF NOT EXISTS `ssafy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssafy`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: i7d207.p.ssafy.io    Database: ssafy
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `age_portion`
--

DROP TABLE IF EXISTS `age_portion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `age_portion` (
  `ageportion_id` bigint NOT NULL AUTO_INCREMENT,
  `female10` varchar(10) DEFAULT NULL,
  `female20` varchar(10) DEFAULT NULL,
  `female30` varchar(10) DEFAULT NULL,
  `female40` varchar(10) DEFAULT NULL,
  `female50` varchar(10) DEFAULT NULL,
  `male10` varchar(10) DEFAULT NULL,
  `male20` varchar(10) DEFAULT NULL,
  `male30` varchar(10) DEFAULT NULL,
  `male40` varchar(10) DEFAULT NULL,
  `male50` varchar(10) DEFAULT NULL,
  `movie_code` varchar(255) DEFAULT NULL,
  `ranking` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ageportion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `age_portion`
--

LOCK TABLES `age_portion` WRITE;
/*!40000 ALTER TABLE `age_portion` DISABLE KEYS */;
INSERT INTO `age_portion` VALUES (1,'5962.3516','47698.812','52170.574','31302.346','11924.703','5287.368','42298.945','46264.473','27758.686','10574.736','192151','1'),(2,'42373.9','699169.3','805104.06','444925.94','127121.69','78694.38','1298457.2','1495193.2','826291','236083.16','81888','2'),(3,'27869.97','271732.2','243862.25','104512.39','48772.45','26777.031','261076.05','234299.02','100413.86','46859.8','198413','3'),(4,'42735.547','416671.56','395303.8','170942.19','42735.547','61497.492','599600.56','568851.8','245989.97','61497.492','187347','4'),(5,'35312.105','388433.16','459057.34','223643.34','70624.21','48764.336','536407.7','633936.3','308840.78','97528.67','196367','5'),(6,'152104.73','1774555.1','2028063.1','912628.3','253507.89','228157.08','2661832.8','3042094.5','1368942.6','380261.8','192608','6'),(7,'0','8272.135','8272.135','7416.396','4278.69','0','7335.6655','7335.6655','6576.804','3794.31','217720','7'),(8,'641.205','12503.498','10579.883','5770.8447','2244.2175','783.695','15282.053','12930.967','7053.2554','2742.9324','217472','8'),(9,'82.0224','2542.6943','3116.8513','1394.3809','1066.2913','145.8176','4520.3457','5541.069','2478.8992','1895.6288','216495','9'),(10,'5035.0464','55385.51','44056.656','12587.616','10070.093','4289.114','47180.25','37529.742','10722.784','8578.228','191634','10');
/*!40000 ALTER TABLE `age_portion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` bigint NOT NULL AUTO_INCREMENT,
  `actor` varchar(200) DEFAULT NULL,
  `age_limit` varchar(15) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `director` varchar(200) DEFAULT NULL,
  `eng_title` varchar(100) DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `img` varchar(2000) DEFAULT NULL,
  `img2` varchar(2000) DEFAULT NULL,
  `img3` varchar(2000) DEFAULT NULL,
  `img4` varchar(2000) DEFAULT NULL,
  `img5` varchar(2000) DEFAULT NULL,
  `img6` varchar(2000) DEFAULT NULL,
  `movie_code` varchar(15) DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `running_time` varchar(20) DEFAULT NULL,
  `summary` longtext,
  `title` varchar(200) DEFAULT NULL,
  `total_customer` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'류준열, 김우빈, 김태리','12세 관람가','한국','최동훈','Alienoid, 2022','액션, 판타지, SF','https://movie-phinf.pstatic.net/20220708_75/16572722362230AyHS_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220616_274/16553440662302SpBU_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220617_113/1655427761368j8QHv_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220620_61/1655692158584QeRHN_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220622_187/1655880445298i16AF_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220622_262/1655880463805ndpvh_JPEG/movie_image.jpg','192151','7.04','2022.07.20','142분','2022년 현재, ‘가드’(김우빈)’와 ‘썬더’는 인간의 몸에 가두어진 외계인 죄수를 관리하며 지구에 살고 있다.\n 어느 날, 서울 상공에 우주선이 나타나고\n 형사 ‘문도석’(소지섭)은 기이한 광경을 목격하게 되는데..\n \n 한편, 630년 전 고려에선 얼치기 도사 ‘무륵’(류준열)과 천둥 쏘는 처자 ‘이안’(김태리)이\n 엄청난 현상금이 걸린 신검을 차지하기 위해 서로를 속고 속이는 가운데\n 신검의 비밀을 찾는 두 신선 ‘흑설’(염정아)과 ‘청운’(조우진),\n 가면 속의 ‘자장’(김의성)도 신검 쟁탈전에 나선다.\n 그리고 우주선이 깊은 계곡에서 빛을 내며 떠오르는데…\n \n 2022년 인간 속에 수감된 외계인 죄수를 쫓는 이들\n 1391년 고려 말 소문 속의 신검을 차지하려는 도사들\n \n 시간의 문이 열리고\n 모든 것이 바뀌기 시작했다!','외계+인 1부','281243'),(2,'톰 크루즈, 마일즈 텔러, 제니퍼 코넬리','12세 관람가','미국','조셉 코신스키','Top Gun: Maverick, 2021','액션','https://movie-phinf.pstatic.net/20220509_176/1652081912471yhg3N_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20191219_253/1576735700330webEM_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20191219_176/1576735700763y1rP2_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220524_298/1653356094900NLnQ3_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220518_121/1652840771393vLWSH_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220524_220/1653355431672Fu8QY_JPEG/movie_image.jpg','81888','9.6','2022.06.22','130분','최고의 파일럿이자 전설적인 인물 매버릭(톰 크루즈)은 자신이 졸업한 훈련학교 교관으로 발탁된다.\n 그의 명성을 모르던 팀원들은 매버릭의 지시를 무시하지만 실전을 방불케 하는 상공 훈련에서 눈으로 봐도 믿기 힘든 전설적인 조종 실력에 모두가 압도된다.\n \n 매버릭의 지휘아래 견고한 팀워크를 쌓아가던 팀원들에게 국경을 뛰어넘는 위험한 임무가 주어지자\n 매버릭은 자신이 가르친 동료들과 함께 마지막이 될 지 모를 하늘 위 비행에 나서는데…','탑건: 매버릭','6053414'),(3,'박해일, 탕웨이, 이정현','15세 관람가','한국','박찬욱','Decision To Leave, 2021','멜로/로맨스, 드라마, 서스펜스','https://movie-phinf.pstatic.net/20220607_129/16545872892918GA4h_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220419_135/1650351389788tLmfJ_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220519_10/16529243648849TGmT_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220609_65/1654742095039v0ikC_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220609_119/1654742162936nM9es_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220706_47/1657071875155L2Ftq_JPEG/movie_image.jpg','198413','8.89','2022.06.29','138분','산 정상에서 추락한 한 남자의 변사 사건.\n 담당 형사 \'해준\'(박해일)은\n 사망자의 아내 \'서래\'(탕웨이)와 마주하게 된다.\n \n \"\"산에 가서 안 오면 걱정했어요, 마침내 죽을까 봐.\"\"\n \n 남편의 죽음 앞에서 특별한 동요를 보이지 않는 \'서래\'.\n 경찰은 보통의 유가족과는 다른 \'서래\'를 용의선상에 올린다.\n \'해준\'은 사건 당일의 알리바이 탐문과 신문,\n 잠복수사를 통해 \'서래\'를 알아가면서\n 그녀에 대한 관심이 점점 커져가는 것을 느낀다.\n \n 한편, 좀처럼 속을 짐작하기 어려운 \'서래\'는\n 상대가 자신을 의심한다는 것을 알면서도\n 조금의 망설임도 없이 \'해준\'을 대하는데….\n \n 진심을 숨기는 용의자\n 용의자에게 의심과 관심을 동시에 느끼는 형사\n 그들의 <헤어질 결심>','헤어질 결심','1366175'),(4,'크리스 헴스워스, 나탈리 포트만, 테사 톰슨','12세 관람가','미국','타이카 와이티티','Thor: Love and Thunder, 2022','액션, 모험, 판타지','https://movie-phinf.pstatic.net/20220706_263/1657071698045SKrKH_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220614_235/1655169069627apOxD_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220610_181/1654824482664lUFky_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220608_130/16546506802104gMNH_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_82/1655947039218n57fm_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_194/1655946893700wfReC_JPEG/movie_image.jpg','187347','6.82','2022.07.06','119분','슈퍼 히어로 시절이여, 안녕!\n 이너피스를 위해 자아 찾기 여정을 떠난 천둥의 신 ‘토르’\n \n 그러나, 우주의 모든 신들을 몰살하려는 신 도살자 ‘고르’의 등장으로\n ‘토르’의 안식년 계획은 산산조각 나버린다.\n \n ‘토르’는 새로운 위협에 맞서기 위해,\n ‘킹 발키리’, ‘코르그’, 그리고 전 여자친구 ‘제인’과 재회하게 되는데,\n 그녀가 묠니르를 휘두르는 ‘마이티 토르’가 되어 나타나 모두를 놀라게 한다.\n \n 이제, 팀 토르는 ‘고르’의 복수에 얽힌 미스터리를 밝히고\n 더 큰 전쟁을 막기 위한 전 우주적 스케일의 모험을 시작하는데...\n \n 우주 최고의 ‘갓’ 매치가 시작된다!','토르: 러브 앤 썬더','2605826'),(5,'신시아, 박은빈, 서은수','15세 관람가','한국','박훈정','The Witch : Part2. The Other One, 2021','액션','https://movie-phinf.pstatic.net/20220615_63/1655270906406BGdFF_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220517_160/1652750945934UB6en_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220517_136/1652750969397RzlWv_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220518_143/1652836227169XPYfa_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220531_290/16539627394198fznB_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220523_255/1653272363064C9EFE_JPEG/movie_image.jpg','196367','6.98','2022.06.15','137분','’자윤’이 사라진 뒤,\n 정체불명의 집단의 무차별 습격으로 마녀 프로젝트가 진행되고 있는 ‘아크’가 초토화된다.\n 그곳에서 홀로 살아남은 ‘소녀’는 생애 처음 세상 밖으로 발을 내딛고 우연히 만난 ‘경희’의 도움으로 농장에서 지내며 따뜻한 일상에 적응해간다.\n 한편, ‘소녀’가 망실되자 행방을 쫓는 책임자 ‘장’과 마녀 프로젝트의 창시자 ‘백총괄’의 지령을 받고 제거에 나선 본사 요원 ‘조현’, ‘경희’의 농장 소유권을 노리는 조직의 보스 ‘용두’와 상해에서 온 의문의 4인방까지 각기 다른 목적을 지닌 세력이 하나 둘 모여들기 시작하면서 ‘소녀’ 안에 숨겨진 본성이 깨어나는데…\n \n 모든 것의 시작,\n 더욱 거대하고 강력해진 마녀가 온다.','마녀(魔女) Part2. The Other One','2802548'),(6,'마동석, 손석구, 최귀화','15세 관람가','한국','이상용','the roundup, 2022','범죄, 액션','https://movie-phinf.pstatic.net/20220516_144/1652665409592Chvey_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220426_19/16509365610560EIGm_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220426_89/1650936584302EbdIF_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220426_87/1650936606581h5FqT_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220426_184/1650936656952WBd3u_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220426_147/1650936674605FBQhg_JPEG/movie_image.jpg','192608','9.03','2022.05.18','106분','가리봉동 소탕작전 후 4년 뒤,\n 금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는다.\n \n 괴물형사 ‘마석도’(마동석)와 ‘전일만’(최귀화) 반장은 현지 용의자에게서 수상함을 느끼고,\n 그의 뒤에 무자비한 악행을 벌이는 ‘강해상’(손석구)이 있음을 알게 된다.\n \n ‘마석도’와 금천서 강력반은 한국과 베트남을 오가며\n 역대급 범죄를 저지르는 ‘강해상’을 본격적으로 쫓기 시작하는데...\n \n 나쁜 놈들 잡는 데 국경 없다!\n 통쾌하고 화끈한 범죄 소탕 작전이 다시 펼쳐진다!','범죄도시2','12675394'),(7,'장혁, 브루스 칸, 이서영','청소년 관람불가','한국','최재훈','THE KILLER _ A GIRL WHO DESERVES TO DIE, 2022','액션','https://movie-phinf.pstatic.net/20220713_233/16576932321555t5fq_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220602_96/16541339284464JPTB_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220602_280/1654133974003FziNz_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220603_123/1654220163639XvMwu_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220603_80/16542203306335fxXN_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220704_283/16568982097260I5MT_JPEG/movie_image.jpg','217720','9.04','2022.07.13','95분','은퇴 후 성공적인 재테크로 호화롭게 생활하는 전설의 킬러 ‘의강’이\n 제멋대로 행동하는 여고생 ‘윤지’를 떠맡게 된다.\n \n 단기간 보호자 역할만 하면 될 거라고 가볍게 여긴 순간\n ‘윤지’가 납치되는 사건이 일어나는데…\n \n “걔는 다치면 안 되는 아이야”\n 애써 잠재운 ‘의강’의 본능이 깨어난다!','더 킬러: 죽어도 되는 아이','53820'),(8,'오스틴 버틀러, 톰 행크스','15세 관람가','오스트레일리아, 미국','바즈 루어만','ELVIS, 2022','드라마','https://movie-phinf.pstatic.net/20220701_282/1656663610600xPG8g_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220623_251/1655961233109dDYke_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_172/1655961295091xvHxd_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_221/16559613133022OQ9x_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_46/1655961329212kJSEn_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220623_254/1655961346815nlUQ6_JPEG/movie_image.jpg','217472','8.72','2022.07.13','159분','미국 남부 멤피스에서 트럭을 몰며 음악의 꿈을 키우던 19살의 무명 가수 ‘엘비스’.\n 지역 라디오의 작은 무대에 서게 된 ‘엘비스’는 어디서도 본 적 없는 몸짓과 퍼포먼스로 무대를 압도하고,\n 그에게 매료된 관객들에게 뜨거운 환호성을 받는다.\n 쇼 비즈니스 업계에서 일하던 ‘톰 파커’는 이를 목격하고\n ‘엘비스’에게 스타로 만들어주겠다고 약속하며 함께할 것을 제안한다.\n \n 자신이 자라난 동네에서 보고 들은 흑인음악을 접목시킨\n 독특한 음색과 리듬, 강렬한 퍼포먼스, 화려한 패션까지\n 그의 모든 것이 대중을 사로잡으며 ‘엘비스’는 단숨에 스타의 반열에 올라선다.\n 그러나 시대를 앞서 나간 치명적이고 반항적인 존재감은 혼란스러운 시대 상황과 갈등을 빚게 되고\n 지금껏 쌓아온 모든 것이 한순간에 무너질 수 있다고 압박하는 ‘톰 파커’까지 가세해\n ‘엘비스’는 그의 뜻과는 다른 선택을 하게 된다.\n 이로 인해 평생을 함께한 매니저 ‘톰 파커’와의 관계도 조금씩 어긋나기 시작하는데…','엘비스','71245'),(9,'라이언 고슬링, 크리스 에반스, 아나 데 아르마스','15세 관람가','미국','앤서니 루소','The Gray Man, 2022','액션, 스릴러','https://movie-phinf.pstatic.net/20220707_187/165715990860275QAG_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220427_290/1651041163102VXACx_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220427_82/1651041189450SHLFl_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220427_75/1651041215842snKk9_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220427_194/1651041261766e3HKo_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220712_267/1657612708881BMNv1_JPEG/movie_image.jpg','216495','8.03','2022.07.13','127분','그 누구도 실체를 몰라 `그레이 맨`으로 불리는 CIA의 암살 전문 요원이 우연히 CIA의 감추고 싶은 비밀을 알게 되고, CIA의 사주를 받은 소시오패스 전 동료에게 쫓기며 시작되는 액션 블록버스터','그레이 맨','22784'),(10,'스티브 카렐, 타라지 P. 헨슨, 양자경','전체 관람가','미국','카일 발다','Minions: The Rise of Gru, 2022','애니메이션, 모험, 코미디','https://movie-phinf.pstatic.net/20220720_283/1658284839003UzxoT_JPEG/movie_image.jpg?type=m886_590_2','https://movie-phinf.pstatic.net/20220627_204/1656296812133bE8Py_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220627_11/1656296828670ARGa9_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220627_63/1656296844511wLIoy_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220713_16/1657675984652MGa5W_JPEG/movie_image.jpg','https://movie-phinf.pstatic.net/20220715_76/1657849006229HsIfR_JPEG/movie_image.jpg','191634','7.97','2022.07.20','87분','세계 최고의 슈퍼 악당을 꿈꾸는 미니보스 ‘그루’와 그를 따라다니는 미니언들.\n 어느 날 그루는 최고의 악당 조직 ‘빌런6’의 마법 스톤을 훔치는데 성공하지만\n 뉴페이스 미니언 ‘오토’의 실수로 스톤을 잃어버리고 빌런6에게 납치까지 당한다.\n 미니보스를 구하기 위해 잃어버린 스톤을 되찾아야 하는 ‘오토’, 그리고 쿵푸를 마스터해야 하는 ‘케빈’, ‘스튜어트’, ‘밥’!\n 올여름 극장가를 점령할 MCU(미니언즈 시네마틱 유니버스)가 돌아온다!','미니언즈2','233104');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_playing_info`
--

DROP TABLE IF EXISTS `movie_playing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_playing_info` (
  `movieplayinginfo_id` bigint NOT NULL AUTO_INCREMENT,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `theater_no` varchar(255) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  PRIMARY KEY (`movieplayinginfo_id`),
  KEY `FKkrcis9f4gmyyo0361tlsbumpe` (`movie_id`),
  CONSTRAINT `FKkrcis9f4gmyyo0361tlsbumpe` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_playing_info`
--

LOCK TABLES `movie_playing_info` WRITE;
/*!40000 ALTER TABLE `movie_playing_info` DISABLE KEYS */;
INSERT INTO `movie_playing_info` VALUES (1,'11:12','08:50','1',1),(2,'14:42','12:20','1',1),(3,'17:42','15:20','1',1),(4,'22:02','19:40','1',1),(5,'01:52','23:30','1',1),(6,'10:50','08:40','2',2),(7,'13:20','11:10','2',2),(8,'16:10','14:00','2',2),(9,'18:50','16:40','2',2),(10,'21:30','19:20','2',2),(11,'01:10','23:00','2',2),(12,'11:18','09:00','3',3),(13,'14:28','12:10','3',3),(14,'17:08','14:50','3',3),(15,'19:48','17:30','3',3),(16,'22:28','20:10','3',3),(17,'00:58','22:40','3',3),(18,'11:29','09:30','4',4),(19,'13:49','11:50','4',4),(20,'16:09','14:10','4',4),(21,'18:29','16:30','4',4),(22,'20:59','19:00','4',4),(23,'00:59','23:00','4',4),(24,'11:07','08:50','5',5),(25,'13:47','11:30','5',5),(26,'16:27','14:10','5',5),(27,'19:07','16:50','5',5),(28,'21:37','19:20','5',5),(29,'00:57','22:40','5',5),(30,'11:46','10:00','6',6),(31,'15:06','13:20','6',6),(32,'18:26','16:40','6',6),(33,'21:46','20:00','6',6),(34,'00:46','23:00','6',6),(35,'12:35','11:00','7',7),(36,'16:35','15:00','7',7),(37,'21:55','20:20','7',7),(38,'12:59','10:20','8',8),(39,'18:09','15:30','8',8),(40,'00:29','21:50','8',8),(41,'16:27','14:20','9',9),(42,'22:17','20:10','9',9),(43,'19:07','17:40','10',10);
/*!40000 ALTER TABLE `movie_playing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_recommend`
--

DROP TABLE IF EXISTS `movie_recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_recommend` (
  `movie_recommend_id` bigint NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_recommend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_recommend`
--

LOCK TABLES `movie_recommend` WRITE;
/*!40000 ALTER TABLE `movie_recommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sensor` (
  `sensor_id` bigint NOT NULL,
  `length_value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
INSERT INTO `sensor` VALUES (1,'171');
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `classification` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `reservation_time` datetime(6) DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  `movieplayinginfo_id` bigint DEFAULT NULL,
  `total_cost` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FKmlh467qhgpka25dean091tlo9` (`movieplayinginfo_id`),
  CONSTRAINT `FKmlh467qhgpka25dean091tlo9` FOREIGN KEY (`movieplayinginfo_id`) REFERENCES `movie_playing_info` (`movieplayinginfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'ADULT','01097072047','2022-08-05 16:36:09.567000','B03',1,NULL),(2,'ADULT,ADULT,KIDS','01059278503','2022-08-05 16:38:58.069000','C02,C03,C04',1,NULL),(3,'ADULT,ADULT,KIDS','01059223503','2022-08-05 16:49:49.408000','C03,C04,C05',9,NULL),(4,'ADULT,ADULT','01033223503','2022-08-05 16:50:14.202000','D01,D02',9,NULL),(5,'ADULT,ADULT,ADULT,ADULT,ADULT','01063876453','2022-08-05 16:51:02.799000','A01,A02,A03,A04,A05',9,NULL),(6,'ADULT,ADULT','01099562234','2022-08-05 16:51:43.109000','C01,C02',10,NULL),(7,'ADULT,ADULT','01091532234','2022-08-05 16:51:55.288000','D01,D02',10,NULL),(8,'ADULT,ADULT','01096322234','2022-08-05 16:52:12.007000','A04,A05',10,NULL),(9,'ADULT','01038956363','2022-08-05 16:52:42.470000','A01',10,NULL),(10,'ADULT,KIDS,KIDS','01032289366','2022-08-05 16:53:32.164000','B03,B04,B05',10,NULL),(11,'ADULT,ADULT,ADULT','01032974366','2022-08-05 16:54:51.298000','D03,D04,D05',11,NULL),(12,'ADULT,ADULT,KIDS,KIDS','01098765432','2022-08-05 16:55:43.579000','B01,B02,B03,B04',11,NULL),(13,'ADULT,ADULT','01042565432','2022-08-05 17:01:14.301000','B01,B02',14,NULL),(14,'ADULT,ADULT','01042565432','2022-08-05 17:01:18.367000','D01,D02',14,NULL),(15,'ADULT,ADULT','01042145432','2022-08-05 17:01:27.072000','D04,D05',14,NULL),(16,'ADULT,ADULT','01043445432','2022-08-05 17:01:42.889000','C04,C05',14,NULL),(17,'ADULT,DISABLED','01093859234','2022-08-05 17:11:42.574000','A01,A02',15,NULL),(18,'ADULT,ADULT','01067658234','2022-08-05 17:11:58.229000','B01,B02',15,NULL),(19,'ADULT,KIDS','01067615234','2022-08-05 17:12:07.982000','C01,C02',15,NULL),(20,'ADULT,ADULT','01061125234','2022-08-05 17:12:19.024000','D01,D02',15,NULL),(21,'ADULT,ADULT','01061121524','2022-08-05 17:12:40.620000','A04,A05',15,NULL),(22,'ADULT,ADULT,ADULT','01065377524','2022-08-05 17:13:29.905000','B03,B04,B05',15,NULL),(23,'ADULT,ADULT,KIDS','01049377524','2022-08-05 17:13:50.819000','D03,D04,D05',15,NULL),(24,'ADULT','01023685564','2022-08-05 17:14:20.461000','C05',15,NULL),(25,'ADULT,ADULT,ADULT,ADULT,KIDS,KIDS','01092845564','2022-08-05 17:25:39.776000','B02,B03,B04,C02,C03,C04',21,NULL),(26,'ADULT,ADULT','01053645564','2022-08-05 17:26:21.602000','B02,B03',16,NULL),(27,'ADULT,ADULT','01053345564','2022-08-05 17:26:36.619000','C03,C04',16,NULL),(28,'ADULT,ADULT,ADULT,ADULT','01053342564','2022-08-05 17:27:21.106000','A01,A02,A03,A04',16,NULL),(29,'ADULT,DISABLED','01093756992','2022-08-05 17:28:25.402000','D03,D04',16,NULL),(30,'ADULT,ADULT,ADULT','01093115992','2022-08-05 17:28:55.394000','D03,D04,D05',22,NULL),(31,'ADULT,ADULT','01022115112','2022-08-05 17:29:19.643000','B03,B04',22,NULL),(32,'ADULT,DISABLED','01052584112','2022-08-05 17:30:08.060000','A03,A04',22,NULL),(33,'ADULT,ADULT,ADULT,ADULT','01052584112','2022-08-05 17:30:37.002000','C01,C02,C03,C04',22,NULL),(34,'ADULT,ADULT,ADULT','01051784292','2022-08-05 17:31:18.106000','C03,C04,C05',23,NULL),(35,'ADULT,ADULT','01068884292','2022-08-05 17:31:39.215000','D01,D02',23,NULL),(36,'ADULT,ADULT','01026764155','2022-08-05 17:31:55.771000','D04,D05',23,NULL),(37,'ADULT,KIDS','01092464155','2022-08-05 17:32:12.887000','B03,B04',23,NULL),(38,'ADULT','01057464155','2022-08-05 17:32:46.464000','A01',23,NULL),(39,'ADULT,ADULT,KIDS','01050559647','2022-08-05 17:33:11.894000','A03,A04,A05',23,NULL),(51,'ADULT','+821067302821','2022-08-17 14:06:07.535000','C03',5,'12000'),(52,'ADULT','+821067302821','2022-08-17 14:06:29.676000','C03',5,'12000'),(53,'ADULT','+821067302821','2022-08-17 14:07:08.598000','B04',5,'12000'),(54,'ADULT','+821067302821','2022-08-17 14:07:47.393000','C04',5,'12000'),(55,'ADULT','+821067302821','2022-08-17 14:51:50.830000','B04',9,'12000'),(56,'ADULT','+821067302821','2022-08-17 14:52:23.317000','C04',8,'12000'),(57,'ADULT,KIDS,','','2022-08-18 01:21:52.987000','A01,A02',2,'22000'),(58,'ADULT,KIDS,','','2022-08-18 01:24:00.149000','A01,A02',2,'22000'),(59,'ADULT,KIDS','','2022-08-18 01:27:45.583000','A01,A02',2,'22000'),(60,'ADULT','','2022-08-18 01:30:18.378000','B01',2,'12000'),(61,'ADULT','','2022-08-18 01:31:33.438000','B01',2,'12000'),(62,'ADULT,KIDS','','2022-08-18 01:31:57.119000','A01,B01',3,'22000'),(63,'ADULT,ADULT,KIDS,KIDS','','2022-08-18 01:32:52.424000','A01,A02,B01,B02',1,'44000'),(64,'ADULT','','2022-08-18 05:13:12.305000','D03',10,'12000'),(65,'ADULT,KIDS','','2022-08-18 05:36:47.092000','B01,B02',10,'22000'),(66,'ADULT,KIDS','','2022-08-18 05:37:36.895000','B01,C02',9,'22000'),(67,'ADULT,KIDS','','2022-08-18 05:37:42.554000','B01,C02',9,'22000'),(68,'ADULT,KIDS,KIDS','','2022-08-18 05:42:48.861000','00,D03,D04',9,'32000'),(69,'ADULT,ADULT','','2022-08-18 06:11:43.229000','B03,B04',26,'24000'),(70,'KIDS','','2022-08-18 06:28:05.080000','A03',10,'10000'),(71,'KIDS,KIDS','','2022-08-18 13:47:09.116000','C04,D00',11,'20000'),(72,'KIDS','','2022-08-18 13:49:39.230000','C00',11,'10000'),(73,'KIDS,KIDS','','2022-08-18 13:53:15.746000','C03,D00',10,'20000'),(74,'ADULT,ADULT','','2022-08-18 13:53:32.144000','C03,D02',11,'24000'),(75,'ADULT','','2022-08-18 13:58:49.387000','00',9,'12000'),(76,'ADULT,ADULT','','2022-08-18 14:03:28.646000','A03,A04',3,'24000');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `authority` int DEFAULT NULL,
  `last_logged_in` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(25) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,NULL,'string123','string123','string123','string123'),(2,0,NULL,'124124','string1dsfs23','string1sdfs23','string12fsdf3'),(3,0,NULL,'ssdfsdftring','strsdfsdfsding','strfsdfdfsing','stridsfsdfng');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19  0:22:54
