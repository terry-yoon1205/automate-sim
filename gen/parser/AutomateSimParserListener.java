// Generated from /Users/Admin/Desktop/courses/2023w2/cpsc410/project1/src/parser/AutomateSimParser.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutomateSimParser}.
 */
public interface AutomateSimParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AutomateSimParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AutomateSimParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(AutomateSimParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(AutomateSimParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(AutomateSimParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(AutomateSimParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(AutomateSimParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(AutomateSimParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#enum}.
	 * @param ctx the parse tree
	 */
	void enterEnum(AutomateSimParser.EnumContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#enum}.
	 * @param ctx the parse tree
	 */
	void exitEnum(AutomateSimParser.EnumContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(AutomateSimParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(AutomateSimParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(AutomateSimParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(AutomateSimParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(AutomateSimParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(AutomateSimParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#room}.
	 * @param ctx the parse tree
	 */
	void enterRoom(AutomateSimParser.RoomContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#room}.
	 * @param ctx the parse tree
	 */
	void exitRoom(AutomateSimParser.RoomContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#device}.
	 * @param ctx the parse tree
	 */
	void enterDevice(AutomateSimParser.DeviceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#device}.
	 * @param ctx the parse tree
	 */
	void exitDevice(AutomateSimParser.DeviceContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#device_prop}.
	 * @param ctx the parse tree
	 */
	void enterDevice_prop(AutomateSimParser.Device_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#device_prop}.
	 * @param ctx the parse tree
	 */
	void exitDevice_prop(AutomateSimParser.Device_propContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AutomateSimParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AutomateSimParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(AutomateSimParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(AutomateSimParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(AutomateSimParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(AutomateSimParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#for}.
	 * @param ctx the parse tree
	 */
	void enterFor(AutomateSimParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#for}.
	 * @param ctx the parse tree
	 */
	void exitFor(AutomateSimParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomateSimParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(AutomateSimParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomateSimParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(AutomateSimParser.SetContext ctx);
}