package org.bitcoinj.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "DigiByte";
    public static final String coinTicker = "DGB";
    public static final String coinURIScheme = "digibyte";
    public static final String cryptsyMarketId = "139";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://digiexplorer.info";    //block explorer
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";             //block explorer address path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";              //block explorer transaction path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                 //block explorer block path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = BLOCKEXPLORER_BASE_URL_PROD;

    public static final String DONATION_ADDRESS = "DSZpUVTYNNB2A6H3StQkFtSmQMHJddSXhn";  // donation DGB address

    enum CoinHash {
        SHA256,
        scrypt,
        x11,
        custom
    };
    public static final CoinHash coinPOWHash = CoinHash.custom;

    public static boolean checkpointFileSupport = true;
    public static int checkpointDaysBack = 7;

    //Original Values
    public static final int TARGET_TIMESPAN = (int)(0.10 * 24 * 60 * 60);  // 72 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 60);  // 40 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //108 blocks
	
	  //Values after retarget adjustments
	  public static final int TARGET_TIMESPAN_2 = (int)(1 * 60);  // 72 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING_2 = (int)(1 * 60);  // 40 seconds per block.
    public static final int INTERVAL_2 = TARGET_TIMESPAN_2 / TARGET_SPACING_2;  //108 blocks

    public static final int TARGET_TIMESPAN_MULTIALGO = (int)(150);  // 2.5 hours per difficulty cycle, on average.
    public static final int TARGET_SPACING_MULTIALGO = (int)(150);  // 2.5 minutes seconds per block.
    public static final int INTERVAL_MULTIALGO = TARGET_TIMESPAN_MULTIALGO / TARGET_SPACING_MULTIALGO;  //1 blocks
	
	 //Block retarget adjustments take effect
	 public static final int nDiffChangeTarget = 67200; // Patch effective @ block 67200

	//Block multiAlgo
	public static final int nMultiAlgoChangeTarget = 145000; // Patch effective @ block 67200
	
	public static final int nMultiAlgoChangeTarget2 = 400000; // Patch effective @ block 67200

	 //Duration of blocks between reward deductions
	 public static final int patchBlockRewardDuration = 10080; // 10080 blocks main net change

    static final long nAveragingInterval = 10; // 10 blocks
    static final long nAveragingTargetTimespan = nAveragingInterval * TARGET_SPACING_MULTIALGO; // 25 minutes

    static final long nMaxAdjustDown = 40; // 4% adjustment down
    static final long nMaxAdjustUp = 20; // 2% adjustment up
	
	static final long nMaxAdjustDownV3 = 16; // 4% adjustment down
    static final long nMaxAdjustUpV3 = 8; // 2% adjustment up
	static final long nLocalDifficultyAdjustment = 4; // 4% down, 16% up

    static final long nMinActualTimespan = nAveragingTargetTimespan * (100 - nMaxAdjustUp) / 100;
    static final long nMaxActualTimespan = nAveragingTargetTimespan * (100 + nMaxAdjustDown) / 100;
	
	static final long nMinActualTimespanV3 = nAveragingTargetTimespan * (100 - nMaxAdjustUpV3) / 100;
    static final long nMaxActualTimespanV3 = nAveragingTargetTimespan * (100 + nMaxAdjustDownV3) / 100;

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;      //108
    }
    public static final int getIntervalCheckpoints() {
            return 2000;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            if(height > nMultiAlgoChangeTarget) {
                return TARGET_TIMESPAN_MULTIALGO;
            } else if(height > nDiffChangeTarget) {
                return TARGET_TIMESPAN_2;
            } else {
                return TARGET_TIMESPAN;    //72 min
            }
    }
    public static BigInteger getProofOfWorkLimit(int algo)
    {
       return proofOfWorkLimits[algo];
    }

		//need to look into
    public static int spendableCoinbaseDepth = 8; //main.h: static const int COINBASE_MATURITY
    public static final int MAX_COINS = 2000000000;                 //main.h:  MAX_MONEY

    public static final Coin DEFAULT_MIN_TX_FEE = Coin.valueOf(100000);   // MIN_TX_FEE
    public static final Coin DUST_LIMIT = Coin.valueOf(1000); //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION
    public static final int BIP0031_VERSION = 60000;

    public static final int BLOCK_CURRENTVERSION = 2;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = false; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 12024;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 12025;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 30;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final boolean allowBitcoinPrivateKey = false; //for backward compatibility with previous version of digitalcoin
    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfac3b6da;      //0xfa, 0xc3, 0xb6, 0xda

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1389388394L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (2447652);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "7497ea1b465eb39f1c8f507bc877078fe016d6fcb6dfad3a64c98dcc6e1e8496"; 	 //main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "72ddd9496b004221ed0557358846d9248ecd4c440ebd28ed901efc18757d0fad";  
	static public int genesisBlockValue = 8000;                                                              //main.cpp: LoadBlockIndex
    static public String genesisTxInBytes = "04ffff001d01044555534120546f6461793a2031302f4a616e2f323031342c205461726765743a20446174612073746f6c656e2066726f6d20757020746f203131304d20637573746f6d657273";
    static public String genesisTxOutBytes = "00";
	
    

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
					"seed1.digibyte.co", 
					"seed2.hashdragon.com",
					"dgb.cryptoservices.net",
					"digibytewiki.com",
					"digiexplorer.info"

					
    };

    public static int minBroadcastConnections = 1;   //0 for default; we need more peers.

    //
    // TestNet - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfcc1b7dc;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 999999L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (99999);                         //main.cpp: LoadBlockIndex



	public static final Coin GetDGBSubsidy(int nHeight) 
	{	
		//initial coin value
		int iSubsidy = 8000;	
		
		int blocks = nHeight - nDiffChangeTarget;
		int weeks = (blocks / patchBlockRewardDuration)+1;
		
		//decrease reward by 0.5% every week
		for(int i = 0; i < weeks; i++) {
			iSubsidy -= (iSubsidy/200); 
		}
		Coin qSubsidy = Coin.valueOf(iSubsidy, 0);
		return qSubsidy;
	}

    public static final Coin GetBlockReward(int nHeight)
    {	
		Coin nSubsidy = Coin.valueOf(8000, 0);
		
		if(nHeight < nDiffChangeTarget) 
		{
			//this is pre-patch, reward is 8000.
			
			if(nHeight < 1440)  //1440
			{
				nSubsidy = Coin.valueOf(72000, 0);
			}
			else if(nHeight < 5760)  //5760
			{
				nSubsidy = Coin.valueOf(16000, 0);
			}
      
		} else 
		{
			//patch takes effect after 68,250 blocks solved
			nSubsidy = GetDGBSubsidy(nHeight);
		}

        return nSubsidy;
    }
	
	public static int subsidyDecreaseBlockCount = 100000;


    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12
    public static BigInteger [] proofOfWorkLimits = new BigInteger[] {
        proofOfWorkLimit,proofOfWorkLimit,proofOfWorkLimit,proofOfWorkLimit,proofOfWorkLimit };

    /*proofOfWorkLimits[Block.ALGO_SHA256D] = proofOfWorkLimit;
    proofOfWorkLimits[Block.ALGO_SCRYPT]  = proofOfWorkLimit;
    proofOfWorkLimits[ALGO_GROESTL] = proofOfWorkLimit;
    proofOfWorkLimits[ALGO_SKEIN]   = proofOfWorkLimit;
    proofOfWorkLimits[ALGO_QUBIT]   = proofOfWorkLimit;*/



    static public String[] testnetDnsSeeds = new String[] {
            "testseed1.digibyte.org"
    };
       //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";
    public static final String TESTNET_SATOSHI_KEY = "";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.digibyte.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.digibyte.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.digibyte.unittest";	

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {

        //checkpoints.put(     0, new Sha256Hash("00000ffde4c020b5938441a0ea3d314bf619eff0b38f32f78f7583cffa1ea485"));
        //checkpoints.put(  9646, new Sha256Hash("0000000000000b0f1372211861f226a3ec06a27d0a5bf36e4244a982da077e8f"));
        //checkpoints.put( 27255, new Sha256Hash("00000000000005112a0debf53703eb3dc4ec2d8d68599c90db71423ea14489b7"));
        //checkpoints.put( 70623, new Sha256Hash("00000000000004767ff6e509d00772af5c4bedaa82c38c1e95c33adbf5ff84f5"));
        //checkpoints.put(112567, new Sha256Hash("000000000000018c0621bf32ab33d3ca871509f406f08be6dd20facea747b099"));
        //checkpoints.put(141845, new Sha256Hash("00000000000000f62d14d55c2bc3ec0ba94e4f2b3868bbe7be9cb5b681fcc0fb"));
        //checkpoints.put(149540, new Sha256Hash("000000000000061b8f5b8653fe13b5e453347d9386d43d09445ee5e26a8222bb"));
        //checkpoints.put(348178, new Sha256Hash("0000000000000a410c6aff939087769e757132098fa0b0ce89f11d68f935077d"));
        //checkpoints.put(431747, new Sha256Hash("000000000000065616abeccd681f7b4d6d7bed06deef0e1a6e70c905edae3692"));

    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "";

}