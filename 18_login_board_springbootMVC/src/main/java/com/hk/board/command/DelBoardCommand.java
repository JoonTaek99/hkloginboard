package com.hk.board.command;

import java.util.Arrays;

import jakarta.validation.constraints.NotEmpty;

public class DelBoardCommand {
   
   @NotEmpty(message = "최소 하나는 눌러야될거아니야")
   private String[] seq;
   
   public DelBoardCommand() {
      super();
      // TODO Auto-generated constructor stub
   }

   public DelBoardCommand(@NotEmpty(message = "최소하나 눌러") String[] seq) {
      super();
      this.seq = seq;
   }

   public String[] getSeq() {
      return seq;
   }

   public void setSeq(String[] seq) {
      this.seq = seq;
   }

   @Override
   public String toString() {
      return "DelBoardCommand [seq=" + Arrays.toString(seq) + "]";
   }
   
   
}