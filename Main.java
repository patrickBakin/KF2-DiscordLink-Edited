
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		if(args.length<6)
		{
			System.out.println("It needs to have 6 arguments. containing port, webHookURL,SteamAPIKey,CDAVatarURL(optional),DiscordBotToken(optional),ChannelID(optional)\n"
					+ "ex. java -jar KF2Listener.jar 2424 https://killingfloor2.com/ ad2eSDSGJSU2dfd148 0 0 0\n"
					+ "(place 0 if you don't want to use this functionality)");
			return;
		}
		System.out.println("                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                   dddddddd                                                                         \r\n"
				+ "DDDDDDDDDDDDD          iiii                                                                                        d::::::d     LLLLLLLLLLL               iiii                   kkkkkkkk           \r\n"
				+ "D::::::::::::DDD      i::::i                                                                                       d::::::d     L:::::::::L              i::::i                  k::::::k           \r\n"
				+ "D:::::::::::::::DD     iiii                                                                                        d::::::d     L:::::::::L               iiii                   k::::::k           \r\n"
				+ "DDD:::::DDDDD:::::D                                                                                                d:::::d      LL:::::::LL                                      k::::::k           \r\n"
				+ "  D:::::D    D:::::D iiiiiii     ssssssssss       cccccccccccccccc   ooooooooooo   rrrrr   rrrrrrrrr       ddddddddd:::::d        L:::::L               iiiiiiinnnn  nnnnnnnn     k:::::k    kkkkkkk\r\n"
				+ "  D:::::D     D:::::Di:::::i   ss::::::::::s    cc:::::::::::::::c oo:::::::::::oo r::::rrr:::::::::r    dd::::::::::::::d        L:::::L               i:::::in:::nn::::::::nn   k:::::k   k:::::k \r\n"
				+ "  D:::::D     D:::::D i::::i ss:::::::::::::s  c:::::::::::::::::co:::::::::::::::or:::::::::::::::::r  d::::::::::::::::d        L:::::L                i::::in::::::::::::::nn  k:::::k  k:::::k  \r\n"
				+ "  D:::::D     D:::::D i::::i s::::::ssss:::::sc:::::::cccccc:::::co:::::ooooo:::::orr::::::rrrrr::::::rd:::::::ddddd:::::d        L:::::L                i::::inn:::::::::::::::n k:::::k k:::::k   \r\n"
				+ "  D:::::D     D:::::D i::::i  s:::::s  ssssss c::::::c     ccccccco::::o     o::::o r:::::r     r:::::rd::::::d    d:::::d        L:::::L                i::::i  n:::::nnnn:::::n k::::::k:::::k    \r\n"
				+ "  D:::::D     D:::::D i::::i    s::::::s      c:::::c             o::::o     o::::o r:::::r     rrrrrrrd:::::d     d:::::d        L:::::L                i::::i  n::::n    n::::n k:::::::::::k     \r\n"
				+ "  D:::::D     D:::::D i::::i       s::::::s   c:::::c             o::::o     o::::o r:::::r            d:::::d     d:::::d        L:::::L                i::::i  n::::n    n::::n k:::::::::::k     \r\n"
				+ "  D:::::D    D:::::D  i::::i ssssss   s:::::s c::::::c     ccccccco::::o     o::::o r:::::r            d:::::d     d:::::d        L:::::L         LLLLLL i::::i  n::::n    n::::n k::::::k:::::k    \r\n"
				+ "DDD:::::DDDDD:::::D  i::::::is:::::ssss::::::sc:::::::cccccc:::::co:::::ooooo:::::o r:::::r            d::::::ddddd::::::dd     LL:::::::LLLLLLLLL:::::Li::::::i n::::n    n::::nk::::::k k:::::k   \r\n"
				+ "D:::::::::::::::DD   i::::::is::::::::::::::s  c:::::::::::::::::co:::::::::::::::o r:::::r             d:::::::::::::::::d     L::::::::::::::::::::::Li::::::i n::::n    n::::nk::::::k  k:::::k  \r\n"
				+ "D::::::::::::DDD     i::::::i s:::::::::::ss    cc:::::::::::::::c oo:::::::::::oo  r:::::r              d:::::::::ddd::::d     L::::::::::::::::::::::Li::::::i n::::n    n::::nk::::::k   k:::::k \r\n"
				+ "DDDDDDDDDDDDD        iiiiiiii  sssssssssss        cccccccccccccccc   ooooooooooo    rrrrrrr               ddddddddd   ddddd     LLLLLLLLLLLLLLLLLLLLLLLLiiiiiiii nnnnnn    nnnnnnkkkkkkkk    kkkkkkk\r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "                                                                                                                                                                                                    \r\n"
				+ "bbbbbbbb                                                                                                                                                                                            \r\n"
				+ "b::::::b                                       PPPPPPPPPPPPPPPPP                           tttt                              iiii                      kkkkkkkk                                     \r\n"
				+ "b::::::b                                       P::::::::::::::::P                       ttt:::t                             i::::i                     k::::::k                                     \r\n"
				+ "b::::::b                                       P::::::PPPPPP:::::P                      t:::::t                              iiii                      k::::::k                                     \r\n"
				+ " b:::::b                                       PP:::::P     P:::::P                     t:::::t                                                        k::::::k                                     \r\n"
				+ " b:::::bbbbbbbbb yyyyyyy           yyyyyyy       P::::P     P:::::Paaaaaaaaaaaaa  ttttttt:::::ttttttt   rrrrr   rrrrrrrrr  iiiiiii     cccccccccccccccc k:::::k    kkkkkkk                          \r\n"
				+ " b::::::::::::::bby:::::y         y:::::y        P::::P     P:::::Pa::::::::::::a t:::::::::::::::::t   r::::rrr:::::::::r i:::::i   cc:::::::::::::::c k:::::k   k:::::k                           \r\n"
				+ " b::::::::::::::::by:::::y       y:::::y         P::::PPPPPP:::::P aaaaaaaaa:::::at:::::::::::::::::t   r:::::::::::::::::r i::::i  c:::::::::::::::::c k:::::k  k:::::k                            \r\n"
				+ " b:::::bbbbb:::::::by:::::y     y:::::y          P:::::::::::::PP           a::::atttttt:::::::tttttt   rr::::::rrrrr::::::ri::::i c:::::::cccccc:::::c k:::::k k:::::k                             \r\n"
				+ " b:::::b    b::::::b y:::::y   y:::::y           P::::PPPPPPPPP      aaaaaaa:::::a      t:::::t          r:::::r     r:::::ri::::i c::::::c     ccccccc k::::::k:::::k                              \r\n"
				+ " b:::::b     b:::::b  y:::::y y:::::y            P::::P            aa::::::::::::a      t:::::t          r:::::r     rrrrrrri::::i c:::::c              k:::::::::::k                               \r\n"
				+ " b:::::b     b:::::b   y:::::y:::::y             P::::P           a::::aaaa::::::a      t:::::t          r:::::r            i::::i c:::::c              k:::::::::::k                               \r\n"
				+ " b:::::b     b:::::b    y:::::::::y              P::::P          a::::a    a:::::a      t:::::t    ttttttr:::::r            i::::i c::::::c     ccccccc k::::::k:::::k                              \r\n"
				+ " b:::::bbbbbb::::::b     y:::::::y             PP::::::PP        a::::a    a:::::a      t::::::tttt:::::tr:::::r           i::::::ic:::::::cccccc:::::ck::::::k k:::::k                             \r\n"
				+ " b::::::::::::::::b       y:::::y              P::::::::P        a:::::aaaa::::::a      tt::::::::::::::tr:::::r           i::::::i c:::::::::::::::::ck::::::k  k:::::k   ......                   \r\n"
				+ " b:::::::::::::::b       y:::::y               P::::::::P         a::::::::::aa:::a       tt:::::::::::ttr:::::r           i::::::i  cc:::::::::::::::ck::::::k   k:::::k  .::::.                   \r\n"
				+ " bbbbbbbbbbbbbbbb       y:::::y                PPPPPPPPPP          aaaaaaaaaa  aaaa         ttttttttttt  rrrrrrr           iiiiiiii    cccccccccccccccckkkkkkkk    kkkkkkk ......                   \r\n"
				+ "                       y:::::y                                                                                                                                                                      \r\n"
				+ "                      y:::::y                                                                                                                                                                       \r\n"
				+ "                     y:::::y                                                                                                                                                                        \r\n"
				+ "                    y:::::y                                                                                                                                                                         \r\n"
				+ "                   yyyyyyy                                                                                                                                                                          \r\n"
				+ "                                                                                                                                                                                                        ");
		MainListener mainlistener = new MainListener(Integer.parseInt(args[0]),args[1],args[2],args[3],args[4],args[5]);
		
		
	}

}
