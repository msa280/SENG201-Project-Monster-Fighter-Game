package monsters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.AudioPlayer;

class MonsterTest {
	
	
	private AudioPlayer monsterSound = new AudioPlayer();

	@Test
	void soundTest() 
	{
		monsterSound.playSoundLoop("Monster Audio\\Venomhound.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Soilscreamer.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Cavernfreak.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Mornpest.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Hollowtree.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Magmataur.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Manicboy.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Shapeshifter.wav"); // Working
		monsterSound.playSoundLoop("Monster Audio\\Sleepdemon.wav"); //NOT Working
		monsterSound.playSoundLoop("Monster Audio\\Skulldiablo.wav"); // Working
	    monsterSound.playSoundLoop("Monster Audio\\Spineeater.wav"); // Working
	}

}
